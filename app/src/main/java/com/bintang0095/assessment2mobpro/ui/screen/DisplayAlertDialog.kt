package com.bintang0095.assessment2mobpro.ui.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bintang0095.assessment2mobpro.R

@Composable
fun DisplayAlertDialog(

    onDismissRequest: () -> Unit,

    onConfirmation: () -> Unit
) {

    AlertDialog(

        onDismissRequest = {

            onDismissRequest()
        },

        confirmButton = {

            TextButton(

                onClick = {

                    onConfirmation()
                }

            ) {

                Text(
                    text = stringResource(
                        R.string.delete
                    )
                )
            }
        },

        dismissButton = {

            TextButton(

                onClick = {

                    onDismissRequest()
                }

            ) {

                Text(
                    text = stringResource(
                        R.string.cancel
                    )
                )
            }
        },

        title = {

            Text(
                text = stringResource(
                    R.string.confirm_delete
                )
            )
        }
    )
}