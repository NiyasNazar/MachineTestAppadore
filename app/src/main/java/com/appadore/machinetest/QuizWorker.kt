package com.appadore.machinetest

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters

class QuizWorker (context: Context, workerParams: WorkerParameters) :
Worker(context, workerParams) {

    override fun doWork(): Result {
        // Continue the quiz logic here if needed
        return Result.success()
    }
}

// Enqueue the worker when necessary
