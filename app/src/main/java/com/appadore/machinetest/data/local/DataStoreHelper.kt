package com.appadore.machinetest.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreHelper @Inject constructor(@ApplicationContext context: Context) {

    private var mContext: Context = context
    companion object {
        const val KEY_TOKEN = "token"
        const val KEY_REFRESH_TOKEN = "refresh_token"
        const val KEY_LOGGED_IN = "logged_in"
        const val KEY_IS_FIRST_TIME = "is_first_time"
        const val KEY_HEX_TO_STRING = "hex_to_string"

        const val KEY_APP_PATH = "app_path"
        const val KEY_CURRENT_BASE_URL = "current_base_url"
        const val KEY_USERNAME_PASSWORD = "username_password"
        const val KEY_USERNAME = "username"
        const val KEY_ROLE = "role"
        const val KEY_SCAN_SUFFIX = "suffix"
        const val KEY_SCAN_PREFIX = "prefix"
        const val KEY_TRIM_LENGTH = "trim_length"

        const val KEY_ORGANIZATION_UPDATE = "is_organization_selected"
        const val KEY_SELECTED_SUB_LOCATION = "selected_sub_location"
        const val KEY_PRIMARY_LOCATION = "primary_location"
        const val KEY_PRIMARY_ORGANIZATION = "primary_organization"
        const val KEY_ORGANIZATION_TRANSFER = "organization_transfer"
        const val KEY_READ_RANGE = "read_range"
        const val KEY_TO_LOCATION_TRANSFER_DIRECT = "to_location_transfer_direct"
        const val KEY_OFFLINE_MODE_STATUS = "offline_mode_status"
    }
}