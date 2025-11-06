package ro.pub.cs.systems.eim.colocviu1_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ro.pub.cs.systems.eim.colocviu1_1.ui.theme.Colocviu1_1MainActivityTheme

class Colocviu1_1SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pressed = intent.getStringExtra("but") ?: ""
        val count = intent.getIntExtra("count", 0)

        setContent {
            Colocviu1_1MainActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondUI(
                        Modifier.padding(innerPadding),
                        pressed = pressed,
                        count = count,
                        activity = this,
                    )

                }
            }
        }
    }
}

@Composable
fun SecondUI(
    modifier: Modifier = Modifier,
    pressed: String,
    count: Int,
    activity: ComponentActivity
    ) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Button pressed: $pressed\nNumber of presses: $count")

        Button(
            onClick = {
                val resultIntent = Intent().apply {
                    putExtra("result", "OK")
                }
                activity.setResult(RESULT_REGISTER, resultIntent)
                activity.finish()
            }
        ) {
            Text("OK")
        }

        Button(
            onClick = {
                val resultIntent = Intent().apply {
                    putExtra("result", "OK")
                }
                activity.setResult(RESULT_CANCELED, resultIntent)
                activity.finish()
            }
        ) {
            Text("Cancel")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Colocviu1_1MainActivityTheme {
        SecondUI(
            pressed = "Left, Right, Left, ",
            count = 3,
            activity = ComponentActivity()
        )
    }
}