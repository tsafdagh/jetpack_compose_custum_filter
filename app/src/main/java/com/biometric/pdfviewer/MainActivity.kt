package com.biometric.pdfviewer

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberImagePainter
import com.biometric.pdfviewer.fileDownload.components.HomeFileViewComponent
import com.biometric.pdfviewer.fileDownload.models.CustomFile
import com.biometric.pdfviewer.fileDownload.models.ImageFileFileFromGallery
import com.biometric.pdfviewer.fileDownload.models.ImageFileFromCamera
import com.biometric.pdfviewer.ui.theme.PdfViewerTheme
import com.biometric.pdfviewer.viewFilterOption.NotificationScreen


//Difference beetwen tow date
//https://stackoverflow.com/questions/10690370/how-do-i-get-difference-between-two-dates-in-android-tried-every-thing-and-pos

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PdfViewerTheme {

                NotificationScreen()
                // A surface container using the 'background' color from the theme

           /*     Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    sectAndShowImageMainComponent()

                    val permissionLauncher = rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { isGranted: Boolean ->
                        if (isGranted) {
                            // Permission Accepted
                        } else {
                            // Permission Denied
                        }
                    }

                    SideEffect {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }

            */
            }
        }
    }
}


@Composable
fun sectAndShowImageMainComponent() {

    val custumFile = CustomFile("", null)
    var fileToShow by remember { mutableStateOf(custumFile) }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            it?.let {
                fileToShow = ImageFileFromCamera("CAMERA", it)
            }
        }

    val galeryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri.let {
                fileToShow = ImageFileFileFromGallery("GALLERY", it)
            }

        }


    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ImageCardItem(fileToShow)

        var isAlertVisisble by remember {
            mutableStateOf(false)
        }

        Button(onClick = { isAlertVisisble = !isAlertVisisble }

        ) {
            Text(text = "TAKE A PICTURE")
        }

        if (isAlertVisisble) {

            SelectTypeAlert(onCameraSelected = {
                isAlertVisisble = false
                cameraLauncher.launch()
            },
                onGallerySelected = {
                    isAlertVisisble = false
                    galeryLauncher.launch("image/*")
                })
        }
    }
}

@Composable
fun ImageCardItem(customFile: CustomFile) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(corner = CornerSize(6.dp)))
    ) {
        when (customFile) {
            is ImageFileFromCamera -> {
                Image(
                    bitmap = customFile.bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .background(shape = CircleShape, color = Color.White)
                )
            }
            is ImageFileFileFromGallery -> {
                Image(
                    painter = rememberImagePainter(customFile.Uri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .background(shape = CircleShape, color = Color.White)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectTypeAlert(onCameraSelected: () -> Unit, onGallerySelected: () -> Unit) {
    AlertDialog(
        modifier = Modifier.defaultMinSize(minHeight = 300.dp),
        properties = DialogProperties(
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true,
        ),
        onDismissRequest = {
        },
        title = {
            Text("Take an image from")
        },
        text = {

            Column(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    onCameraSelected()
                }) {
                    Text(text = "SELECT FROM CAMERA")
                }
                Button(onClick = {
                    onGallerySelected()
                }) {
                    Text(text = "SELECT FROM GALLERY")
                }

            }
        },

        confirmButton = {


        },
        dismissButton = {


        },
        shape = RoundedCornerShape(8f),
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PdfViewerTheme {
        Greeting("Android")
    }
}