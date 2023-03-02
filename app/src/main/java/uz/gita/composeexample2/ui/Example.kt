package uz.gita.composeexample2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.composeexample2.R

@Composable
fun Example() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.Green)
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .wrapContentSize(),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomCenter),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = ""
            )
        }
    }

}

@Preview
@Composable
fun ExamplePreview() {
    Example()
}