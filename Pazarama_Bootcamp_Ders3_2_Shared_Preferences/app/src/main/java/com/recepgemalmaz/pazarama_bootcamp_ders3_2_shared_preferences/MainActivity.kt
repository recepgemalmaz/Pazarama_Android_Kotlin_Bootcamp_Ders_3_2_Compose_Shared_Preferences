package com.recepgemalmaz.pazarama_bootcamp_ders3_2_shared_preferences




import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.recepgemalmaz.pazarama_bootcamp_ders3_2_shared_preferences.ui.theme.Pazarama_Bootcamp_Ders3_2_Shared_PreferencesTheme


class MainActivity : ComponentActivity() {

    private lateinit var sp:SharedPreferences
    private lateinit var edt:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sp = this.getPreferences(Context.MODE_PRIVATE)
        sp= this.getSharedPreferences("Renkler", Context.MODE_PRIVATE)
        edt = sp.edit()

        setContent {
            var sldRed = remember { mutableStateOf(0F) }
            var sldGreen = remember { mutableStateOf(0F) }
            var sldBlue = remember { mutableStateOf(0F) }

            Pazarama_Bootcamp_Ders3_2_Shared_PreferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column()
                    {
                        ColorSelectorRed(c = sldRed)
                        ColorSelectorGreen(c = sldGreen)
                        ColorSelectorBlue(c = sldBlue)
                        Text(text = "#%02X%02X%02X".format(
                            sldRed.value.toInt(),
                            sldGreen.value.toInt(),
                            sldBlue.value.toInt()
                        ),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(
                                sldRed.value.toInt(),
                                sldGreen.value.toInt(),
                                sldBlue.value.toInt(),
                                255
                            )
                            ,fontSize = 30.sp

                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        Button(onClick = {
                            edt.putInt("K", sldRed.value.toInt())
                            edt.putInt("Y", sldGreen.value.toInt())
                            edt.putInt("M", sldBlue.value.toInt())
                            edt.commit()
                        })
                        {
                            Text(text = "Kaydet")
                        }

                        Spacer(modifier = Modifier.padding(10.dp))

                        Button(onClick = {
                            sldRed.value = sp.getInt("K", 0).toFloat()
                            sldGreen.value = sp.getInt("Y", 0).toFloat()
                            sldBlue.value = sp.getInt("M", 0).toFloat()
                        })
                        {
                            Text(text = "Geri Yükle")
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ColorSelectorRed(c:MutableState<Float>)
{
    Column()
    {
        Slider(
            value = c.value,
            onValueChange = {
                c.value = it
            },
            valueRange = 0F .. 255F,
            colors = SliderDefaults.colors(Color.Red)
        )
        Spacer(modifier = Modifier.padding(3.dp))
        Text(text = "R: ${c.value.toInt()}")
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun ColorSelectorGreen(c:MutableState<Float>)
{
    Column()
    {
        Slider(
            value = c.value,
            onValueChange = {
                c.value = it
            },
            valueRange = 0F .. 255F,
            colors = SliderDefaults.colors(Color.Green)
        )
        Spacer(modifier = Modifier.padding(3.dp))
        Text(text = "G: ${c.value.toInt()}")
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun ColorSelectorBlue(c:MutableState<Float>)
{
    Column()
    {
        Slider(
            value = c.value,
            onValueChange = {
                c.value = it
            },
            valueRange = 0F .. 255F,
            colors = SliderDefaults.colors(Color.Blue)
        )
        Spacer(modifier = Modifier.padding(3.dp))
        Text(text = "B: ${c.value.toInt()}")
        Spacer(modifier = Modifier.padding(10.dp))
    }
}
