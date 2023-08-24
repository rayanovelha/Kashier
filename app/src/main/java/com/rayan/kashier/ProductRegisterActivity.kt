package com.rayan.kashier

import android.annotation.SuppressLint
import android.icu.math.BigDecimal
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rayan.kashier.components.StandardTopAppBar
import com.rayan.kashier.domain.Combo
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.Restaurant
import com.rayan.kashier.domain.SimpleProduct
import com.rayan.kashier.sample.sampleProduct
import com.rayan.kashier.ui.theme.KashierTheme
import java.text.DecimalFormat

class ProductRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KashierTheme {
                Surface {
                    ProductRegisterScaffold(onSaveClick = { product ->
                        Restaurant.addProduct(product)
                        Log.i("PRODUTO CADASTRADO",Restaurant.listProducts().toString())
                        finish()
                    }, onClick = {
                        finish()
                    })
                }
            }
        }
    }
}

class ProductRegisterUiState {
    val radioOptions = listOf("Produto", "Combo")
    var selectedOption by mutableStateOf(radioOptions[0])

    var name by mutableStateOf("")
    var price by mutableStateOf("")
    var number by mutableStateOf("")
    var description by mutableStateOf("")
    var category by mutableStateOf("")
    var image by mutableStateOf("https://images.pexels.com/photos/14469273/pexels-photo-14469273.jpeg")
}

@Composable
fun CoilImageShower(image: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(200.dp)
            .shadow(4.dp),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun FABWithoutText(icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = Modifier.padding(start = 4.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Composable
fun ExtendedFABWithText(
    text: String,
    icon: ImageVector,
    onSaveClick: (Product) -> Unit = {},
    state: ProductRegisterUiState
) {
    ExtendedFloatingActionButton(
        onClick = {
            val convertedPrice = try {
                java.math.BigDecimal(state.price)
            } catch (e: NumberFormatException) {
                java.math.BigDecimal.ZERO
            }

            lateinit var product: Product

            if (state.selectedOption == state.radioOptions[0]) {
                product = SimpleProduct(
                    id = state.number.toInt(),
                    name = state.name,
                    description = state.description,
                    price = convertedPrice,
                    category = state.category,
                    image = state.image
                )
            } else if (state.selectedOption == state.radioOptions[1]) {
                product = Combo(
                    id = state.number.toInt(),
                    name = state.name,
                    description = state.description,
                    price = convertedPrice,
                    products = sampleProduct,
                    image = state.image
                )
            }
            onSaveClick(product)
        },
        modifier = Modifier.padding(start = 4.dp),
        text = { Text(text) },
        icon = { Icon(imageVector = icon, contentDescription = null) })
}

@Composable
fun SelectorRadioButton(state: ProductRegisterUiState) {
    val radioOptions = state.radioOptions
    var selectedOption = state.selectedOption
    Row(Modifier.selectableGroup(), horizontalArrangement = Arrangement.Center) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = selectedOption == text,
                        onClick = { state.selectedOption = text },
                        role = Role.RadioButton
                    )
                    .padding(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == text,
                    onClick = { state.selectedOption = text })
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

@Composable
fun FormTextField(
    content: Pair<String, ImageVector>,
    text: String,
    onTextChange: (String) -> Unit,
    isEnabled: Boolean
) {
    val hint = content.first
    val icon = content.second

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        },
        label = {
            Text(hint)
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.LightGray,
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences
        ),
        enabled = isEnabled
    )
}

@Composable
fun FormNumberField(
    content: Pair<String, ImageVector>,
    text: String,
    onTextChange: (String) -> Unit,
    isEnabled: Boolean
) {
    val hint = content.first
    val icon = content.second

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        },
        label = {
            Text(hint)
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.LightGray,
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        enabled = isEnabled
    )
}

@Composable
fun FormPriceField(
    content: Pair<String, ImageVector>,
    text: String,
    onTextChange: (String) -> Unit
) {

    val hint = content.first
    val icon = content.second

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        },
        label = {
            Text(hint)
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.LightGray,
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun SimpleProductForm(state: ProductRegisterUiState) {

    val formatter = remember {
        DecimalFormat("#.##")
    }

    Column {
        FormTextField(
            content = "Digite o nome do produto" to Icons.Outlined.Info,
            text = state.name,
            onTextChange = { state.name = it },
            isEnabled = true
        )
        FormPriceField(
            content = "Digite o preço do produto" to Icons.Outlined.AttachMoney,
            text = state.price,
            onTextChange = {
                try {
                    state.price = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        state.price = it
                    }
                }
            },
        )
        FormNumberField(
            content = "Digite o número do produto" to Icons.Outlined.Numbers,
            text = state.number,
            onTextChange = { state.number = it },
            isEnabled = true
        )
        FormTextField(
            content = "Digite a descrição do produto" to Icons.Outlined.Menu,
            text = state.description,
            onTextChange = { state.description = it },
            isEnabled = true
        )
        FormTextField(
            content = "Defina a categoria do produto" to Icons.Outlined.Category,
            text = state.category,
            onTextChange = { state.category = it },
            isEnabled = true
        )
    }
}

@Composable
fun ComboForm(state: ProductRegisterUiState) {

    val formatter = remember {
        DecimalFormat("#.##")
    }

    Column {
        FormTextField(
            content = "Digite o nome do combo" to Icons.Outlined.Info,
            text = state.name,
            onTextChange = { state.name = it },
            isEnabled = true
        )
        FormPriceField(
            content = "Digite o preço do combo" to Icons.Outlined.AttachMoney,
            text = state.price,
            onTextChange = {
                try {
                    state.price = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        state.price = it
                    }
                }
            },
        )
        FormNumberField(
            content = "Digite o número do combo" to Icons.Outlined.Numbers,
            text = state.number,
            onTextChange = { state.number = it },
            isEnabled = true
        )
        FormTextField(
            content = "Digite a descrição do combo" to Icons.Outlined.Menu,
            text = state.description,
            onTextChange = { state.description = it },
            isEnabled = true
        )
        FormTextField(
            content = "Categoria: Combo" to Icons.Outlined.Lock,
            text = state.category,
            onTextChange = { state.category = it },
            isEnabled = false
        )
    }
}

@Composable
fun ProductRegisterScreen(state: ProductRegisterUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            //.padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        Spacer(modifier = Modifier)

        SelectorRadioButton(state)

        CoilImageShower(state.image)

        Spacer(modifier = Modifier.size(16.dp))

        val selected = (state.selectedOption == state.radioOptions[1])
        
        Crossfade(targetState = selected, label = "") {
            when (it){
                true ->
                    ComboForm(state = state)
                false ->
                    SimpleProductForm(state = state)
            }
        }

        Spacer(modifier = Modifier.size(64.dp))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductRegisterScaffold(
    onSaveClick: (Product) -> Unit,
    onClick: () -> Unit
) {
    val state = remember {
        ProductRegisterUiState()
    }
    Scaffold(
        floatingActionButton = {
            Row(Modifier.padding(16.dp)) {
                ExtendedFABWithText(
                    text = "Confirmar",
                    icon = Icons.Outlined.Check,
                    onSaveClick,
                    state
                )
                FABWithoutText(icon = Icons.Outlined.Close, onClick)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        topBar = { StandardTopAppBar(R.string.title_activity_product_register.toString()) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            ProductRegisterScreen(state)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ProductRegisterScaffold(onSaveClick = { product ->
        Restaurant.addProduct(product)
    }, onClick = {
    })
}