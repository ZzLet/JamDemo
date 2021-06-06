package top.zzlet.jamdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.zzlet.jamdemo.ui.theme.JamDemoTheme

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JamDemoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title= {Text(text = stringResource(id = R.string.search))}
                        )
                    }
                ) {

                    // A surface container using the 'background' color from the theme
                    DefaultPreview()
                }
            }
        }
    }
}

@Composable
fun SearchInput(list: ArrayList<String>) {
    var name by remember { mutableStateOf("") }
    Surface(color = Color(230, 230, 230, 255)) {
        Column {
            Surface(modifier = Modifier.padding(10.dp)) {
                TextField(
                    value = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(20.dp)),

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        name = it

                    },
                    placeholder = { Text(text = stringResource(id = R.string.input_content_please)) },
                    textStyle = TextStyle(fontWeight = FontWeight.Bold),
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())
            name.apply {
                SearchList(this, list)
            }
        }
    }
}

@Composable
fun SearchList(searchStr: String, list: ArrayList<String>) {
    val showList = arrayListOf<String>()
    for (item in list) {
        if (item.contains(searchStr)) {
            showList.add(item)
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        //为每行添加间隔
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        showList.map {
            item {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Text(text = "$it$it$it", modifier = Modifier.padding(20.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val list = arrayListOf<String>()
    repeat(26) {
        list.add(('A' + it).toString())
    }
    SearchInput(list)
}