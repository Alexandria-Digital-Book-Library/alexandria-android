package io.github.aloussase.alexandria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.aloussase.alexandria.di.AppModule
import io.github.aloussase.alexandria.ui.screens.HomeScreen
import io.github.aloussase.alexandria.ui.theme.AlexandriaTheme
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlexandriaTheme {
                KoinApplication(application = {
                    androidContext(this@MainActivity)
                    modules(
                        AppModule.get
                    )
                }) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        HomeScreen(
                            modifier =
                                Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
