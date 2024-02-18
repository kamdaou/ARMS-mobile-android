package com.amalitech.arms_mobile.ui.views.auth

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.Routes
import com.amalitech.arms_mobile.core.utilities.AuthenticationException
import com.amalitech.arms_mobile.core.utilities.GraphApiResponseError
import com.amalitech.arms_mobile.core.utilities.User
import com.amalitech.arms_mobile.data.datasources.MSGRequestWrapper
import com.amalitech.arms_mobile.ui.components.AppModalSheet
import com.amalitech.arms_mobile.ui.components.ErrorBottomSheet
import com.android.volley.VolleyError
import com.microsoft.identity.client.AcquireTokenSilentParameters
import com.microsoft.identity.client.AuthenticationCallback
import com.microsoft.identity.client.IAccount
import com.microsoft.identity.client.IAuthenticationResult
import com.microsoft.identity.client.IPublicClientApplication
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.client.SignInParameters
import com.microsoft.identity.client.SilentAuthenticationCallback
import com.microsoft.identity.client.exception.MsalException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginInScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    var mAccount: IAccount? = null

    var mSingleAccountApp: ISingleAccountPublicClientApplication? = null
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val isClicked = remember {
        mutableStateOf(false)
    }

    PublicClientApplication.createSingleAccountPublicClientApplication(LocalContext.current,
        R.raw.auth_config_single_account,
        object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
            override fun onCreated(application: ISingleAccountPublicClientApplication) {
                mSingleAccountApp = application
            }

            override fun onError(exception: MsalException?) {
                displayError(exception)
            }

            private fun displayError(exception: MsalException?) {
                val error = exception?.message?.let { AuthenticationException(it) }
                if (error != null) {
                    Log.d("EXCEPTION Auth", "This Fxn Run: ${error.message}")
                }
            }
        })


    val silentParameters = AcquireTokenSilentParameters
        .Builder().withScopes(listOf("user.read"))
        .forceRefresh(true)
        .withCallback(object : SilentAuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult?) {
                if (authenticationResult != null) {
                    val acessToken = authenticationResult.accessToken
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.storeAccessToken(acessToken)
                    }
                    Log.d(TAG, "This is the Silent Token: ${authenticationResult.accessToken}")
                }
            }

            override fun onError(exception: MsalException?) {
                if (exception != null) {
                    isClicked.value = true
                    Log.d(TAG, "Get Silent Exception: ${exception.message} ${mAccount?.idToken}")
                    exception.message?.let { AuthenticationException(it) }
                }
            }
        })

    val builder = SignInParameters
        .builder()
        .withActivity(context as Activity)
        .withLoginHint("")
        .withScope("user.read")
        .withCallback(object : AuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult?) {
                if (authenticationResult != null) {
                    mAccount = authenticationResult.account
                    val token = authenticationResult.accessToken
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.storeAccessToken(token)
                    }
                    Log.d("IToken", "Access Token: $token")
                    Log.d(
                        "User Name",
                        "Access Token: ${mAccount!!.username}, ${mAccount!!.idToken}"
                    )
                    MSGRequestWrapper.callGraphAPI(
                        context,
                        authenticationResult.accessToken,
                        { response ->
                            val user = User.fromJSON(response)
                            viewModel.storeUserData(name = user.givenName)
                        },
                        { error ->
                            displayError(error)
                        })
                    MSGRequestWrapper.callGraphPhotoAPI(
                        context,
                        authenticationResult.accessToken,
                        { response ->
                            Log.d(TAG, "success: $response")
                            val photo = Base64.encodeToString(response, Base64.DEFAULT)
                            viewModel.storeUserData(photo = photo)
                        }, { error ->
                            Log.d(TAG, "error: $error")
                            displayError(error)
                        }
                    )
                    navController.navigate(Routes.Home.route)
                }
            }

            private fun displayError(error: VolleyError?) {
                if (error != null) {
                    error.message?.let { GraphApiResponseError(it) }
                }
            }

            override fun onError(exception: MsalException?) {
                if (exception != null) {
                    exception.message?.let { AuthenticationException(it) }
                }
            }

            override fun onCancel() {

            }
        })

    Scaffold(
        modifier = Modifier
    )
    { padding ->
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(vertical = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(id = R.drawable.amalitech_logo),
                        contentDescription = "arms logo",
                        modifier = Modifier.width(200.dp)
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    HelpActionButton(navController)
                }
                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    text = "Sign In with SSO", style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 32.sp
                    )
                )
                TextWithPadding(
                    text = "Welcome back. Enter your credentials to access " + "your account",
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedButton(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp), onClick = {
                    if (mSingleAccountApp == null) return@OutlinedButton

                    mSingleAccountApp!!.acquireTokenSilentAsync(silentParameters.build())
                    mSingleAccountApp!!.signIn(builder.build())
                }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(17.dp))
                        Image(
                            painter = painterResource(id = R.drawable.microsoft_svg),
                            contentDescription = "Microsoft Logo"
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Sign in with Microsoft",
                                color = Color.DarkGray,
                            )
                        }
                    }

                }
                if (isClicked.value) {
                    ErrorBottomSheet(
                        onClick = {
                            mSingleAccountApp!!.signIn(builder.build())
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HelpActionButton(navController: NavHostController) {
    AppModalSheet(onClickAction = {
        navController.navigate(Routes.HelpSupport.route)
    })
}


@Composable
fun TextWithPadding(
    text: String, color: Color, paddingTop: Dp = 8.dp, // Customize padding values
    paddingBottom: Dp = 8.dp
) {
    Text(
        modifier = Modifier.padding(
            top = paddingTop, bottom = paddingBottom
        ), text = text, color = color
    )
}
