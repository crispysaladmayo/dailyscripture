package com.catholicdaily.app.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.catholicdaily.app.R
import com.catholicdaily.app.data.model.HomeDailyContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    content: HomeDailyContent,
    liturgicalZoneLabel: String,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    val refreshCd = stringResource(R.string.fab_refresh_content_description)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home_title)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onRefresh,
                modifier = Modifier.semantics { contentDescription = refreshCd },
            ) {
                Text("↻")
            }
        },
    ) { inner ->
        Column(
            Modifier
                .padding(inner)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text =
                    stringResource(
                        R.string.liturgical_date_line,
                        liturgicalZoneLabel,
                        content.liturgicalDate,
                    ),
                style = MaterialTheme.typography.titleMedium,
            )

            SectionCard(title = stringResource(R.string.section_readings)) {
                val r = content.readings
                if (r == null) {
                    Text(
                        stringResource(R.string.readings_empty),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                } else {
                    Text(
                        "Bundle: ${r.bundleVersion}",
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        r.sourceAttribution,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    r.blocks.forEach { block ->
                        Text(
                            text = "${block.kind}: ${block.title ?: block.reference ?: ""}",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(top = 8.dp),
                        )
                        Text(block.body, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            SectionCard(title = stringResource(R.string.section_homily)) {
                val h = content.homily
                if (h == null) {
                    Text(
                        stringResource(R.string.homily_empty),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                } else {
                    Text(
                        h.title ?: h.homilyDate,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    if (!h.body.isNullOrBlank()) {
                        Text(h.body, style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Text(
                            stringResource(R.string.homily_link_only),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        TextButton(
                            onClick = { uriHandler.openUri(h.sourceUrl) },
                            enabled = h.sourceUrl.isNotBlank(),
                        ) {
                            Text(stringResource(R.string.homily_open_link))
                        }
                    }
                }
            }

            SectionCard(title = stringResource(R.string.section_renungan)) {
                val e = content.editorial
                if (e == null) {
                    Text(
                        stringResource(R.string.editorial_empty),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                } else {
                    if (e.aiAssisted) {
                        Text(
                            stringResource(R.string.renungan_ai_disclosure),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                    e.title?.let { Text(it, style = MaterialTheme.typography.titleSmall) }
                    Text(e.body, style = MaterialTheme.typography.bodyMedium)
                    Text("— ${e.byline}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            content()
        }
    }
}
