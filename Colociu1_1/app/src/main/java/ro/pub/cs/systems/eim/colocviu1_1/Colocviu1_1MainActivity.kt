package ro.pub.cs.systems.eim.colocviu1_1

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.pub.cs.systems.eim.colocviu1_1.ui.theme.Colocviu1_1MainActivityTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    var pressedButton = mutableStateOf("")
    var nrPresses = mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            pressedButton = mutableStateOf(savedInstanceState.getString("butt", ""))
            nrPresses.intValue = savedInstanceState.getInt("count", 0)
            Log.d(TAG, "onCreate: restored butt=${pressedButton} count=${nrPresses}")
        } else {
            Log.d(TAG, "onCreate: fresh start butt=${pressedButton} count=${nrPresses}")
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Colocviu1_1MainActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Colocviu1_UI(
                        modifier = Modifier.padding(innerPadding),
                        onPress = { direction ->
                            pressedButton.value += "$direction, "
                            nrPresses.intValue += 1
                        },
                        nrPresses = nrPresses.intValue,
                        pressedButton = pressedButton.value
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: butt=${pressedButton} count=${nrPresses}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("butt", pressedButton.value)
        outState.putInt("count", nrPresses.intValue)
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: saved butt=${pressedButton} count=${nrPresses}")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: butt=${pressedButton} count=${nrPresses}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: butt=${pressedButton} count=${nrPresses}")
    }
}

@Composable
fun Colocviu1_UI(
    modifier: Modifier = Modifier,
    onPress: (String) -> Unit = { _ -> },
    nrPresses: Int = 0,
    pressedButton: String = ""
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Buttons would go here
            Button(onClick = {
                onPress("NORTH")
            }) {
                Text("NORTH")
            }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    onPress("WEST")
                }) {
                    Text("WEST")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    onPress("EAST")
                }) {
                    Text("EAST")
                }
            }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                onPress("SOUTH")
            }) {
                Text("SOUTH")
            }
        }

        //unediting space to show what button is pressed based on class var
        Text("Number of presses: $nrPresses")
        Text("Pressed buttons: $pressedButton")


        Spacer(modifier = Modifier.height(20.dp))

        // centered button below the buttons
        Button(onClick = { /* TODO */ }) {
            Text("Go to Second Activity")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Colocviu1_1MainActivityTheme {
        Colocviu1_UI(
        )

    }
}