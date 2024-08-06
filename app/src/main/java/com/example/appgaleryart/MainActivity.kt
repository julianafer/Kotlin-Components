import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgaleryart.ui.theme.AppGaleryArtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGaleryArtTheme {
                ScaffoldLayout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldLayout() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Create", "Settings")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Cyan,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "Juliana Top Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                        )
                    }
                },
                actions = {

                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        floatingActionButton = {

            Example(onClick = { /* do something */ }, Color.Magenta)

        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            val icon = when (item) {
                                "Home" -> Icons.Filled.Home
                                "Create" -> Icons.Filled.Email
                                "Settings" -> Icons.Filled.Settings
                                else -> Icons.Filled.Home
                            }
                            Icon(icon, contentDescription = item, modifier = Modifier.size(24.dp))
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        },

        ) { innerPadding ->
        // Integrate CardMinimalExample here
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp) // Add padding to prevent content from touching edges
        ) {
            PartialBottomSheet()
            CardMinimalExample()
            Spacer(modifier = Modifier.height(16.dp)) // Add spacing if needed
            FilledButtonExample(onClick = { /*TODO*/ }, buttonColor = Color.Red)
          

        }

    }
}

@Composable
fun ScrollContent(modifier: Modifier) {
    // Example content to display below the top bar and above the FAB
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding to prevent content from touching edges
    ) {
        Text("Main")
        Spacer(modifier = Modifier.height(16.dp)) // Add spacing if needed
        // Add more content here
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { showBottomSheet = true }
        ) {
            Text("Veja nossa messagem!")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                Text(
                    "Melhor app feito!",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
@Composable
fun FilledButtonExample(onClick: () -> Unit, buttonColor: Color) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text("Botão")
    }
}

@Composable
fun Example(onClick: () -> Unit, buttonColor: Color) {
    FloatingActionButton(
        onClick = { onClick() },
        contentColor = buttonColor
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Floating action button.")
    }
}

@Composable
fun AssistChipExample() {
    AssistChip(
        onClick = { Log.d("Assist chip", "hello world") },
        label = { Text("Assist chip") },
        leadingIcon = {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}
@Composable
fun CardMinimalExample() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Add padding and fill max width
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Optional elevation
    ) {
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent rutrum diam placerat pretium ornare. Sed ac mi tincidunt, auctor ipsum ac, lacinia nulla. Donec in est venenatis ligula rhoncus elementum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus id congue augue, et pulvinar lacus. In pharetra diam ut magna varius, eget volutpat dui ullamcorper. Quisque non felis in erat tristique tempor. Curabitur cursus sed felis in volutpat. Aenean pulvinar a est sit amet tincidunt. Ut cursus turpis nibh, eu venenatis nunc pulvinar in."
            modifier = Modifier.padding(16.dp) // Padding inside the card
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TopAppBarExamplePreview() {
    AppGaleryArtTheme {
        ScaffoldLayout()
    }
}
