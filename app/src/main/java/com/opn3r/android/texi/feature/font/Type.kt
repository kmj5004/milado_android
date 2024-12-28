import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.SystemFontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opn3r.android.texi.R
import kotlinx.coroutines.delay

val notoSanskr = FontFamily(
    Font(R.font.notosanskr_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.notosanskr_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.notosanskr_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.notosanskr_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.notosanskr_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.notosanskr_medium, FontWeight.Medium, FontStyle.Normal),
)

val customTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = notoSanskr,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = notoSanskr,
        fontWeight = FontWeight.Bold,  
        fontSize = 24.sp
    )
)

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = customTypography,
        content = content
    )
}


