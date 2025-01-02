package com.app.studentevent.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.app.studentevent.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdConstants(
    var eventTitle: String,
    var eventSubTitle: String,
    var eventImage: Int,
    var page: Int
)

object Constants {
    val arrayListAd by lazy {
        arrayListOf(
            AdConstants(
                "Create a Event",
                "Easily make a event & publish your event",
                R.drawable.create_event1,
                1
            ),
            AdConstants(
                "Make Your Day",
                "Easily make a event & publish your event",
                R.drawable.make_your_day2,
                2
            ),
            AdConstants(
                "Book a Event",
                "Easily make a event & publish your event",
                R.drawable.book_event3,
                3
            ),
            AdConstants(
                "Share your Day",
                "Easily make a event & publish your event",
                R.drawable.share_event4,
                4
            ),

            )
    }

    fun Date.currentDate(): String {
        val formatter = SimpleDateFormat("E, MMM dd yyyy 'at' hh:mm a", Locale.getDefault())
        return formatter.format(this)
    }
}

@Composable
fun RoundedIconView(
    icon: ImageVector,
    contentDescription: String?,
    backgroundColor: Color = Color.White,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    size: Int = 40,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconTint,
            modifier = Modifier.size((size - 15).dp) // Adjust icon size relative to the circle
        )
    }
}

@Composable
fun IconFromRight(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 20.dp), horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "star",
            tint = Color.White,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.secondaryContainer
                )
                .clip(
                    RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                )
                .padding(top = 20.dp, start = 5.dp, end = 5.dp, bottom = 10.dp)
        )
    }
}

@Composable
fun DateView(){
    Column(modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White)) {

    }
}