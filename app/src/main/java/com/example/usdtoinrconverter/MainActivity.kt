import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.                                                                                                                                                                                                                           KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.usdtoinrconverter.ui.theme.UsdToInrConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsdToInrConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConverterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConverterScreen(modifier: Modifier = Modifier) {

    var usdInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val conversionRate = 83.0  // 1 USD = 83 INR

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "USD to INR Converter",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = usdInput,
            onValueChange = { usdInput = it },
            label = { Text("Enter USD") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val usdValue = usdInput.toDoubleOrNull()
            result = if (usdValue != null) {
                "₹ %.2f".format(usdValue * conversionRate)
            } else {
                "Please enter a valid number"
            }
        }) {
            Text("Convert")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConverterPreview() {
    UsdToInrConverterTheme {
        ConverterScreen()
    }
}