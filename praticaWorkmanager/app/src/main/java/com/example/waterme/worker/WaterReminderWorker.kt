/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.waterme.worker

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.compose.remote.creation.compose.state.log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.waterme.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log
import com.example.waterme.DELAY_TIME_MILLIS
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


private const val TAG = "WaterReminderWorker"
class WaterReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {
        val plantName = inputData.getString(nameKey)
        makePlantReminderNotification(
            applicationContext.resources.getString(R.string.time_to_water, plantName),
            applicationContext
        )

        return withContext(Dispatchers.IO){
            delay(DELAY_TIME_MILLIS.milliseconds)
            return@withContext try {
                Result.success()
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_applying_water),
                    throwable
                )
                Result.failure()
            }
        }
    }

    companion object {
        const val nameKey = "NAME"
    }
}
