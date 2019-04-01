package com.example.courseapp.core.utils

import android.text.Editable
import android.text.TextWatcher


class SearchTextWatcher : TextWatcher {
    private var toolbarSearchListener: ToolbarSearchListener? = null

    fun setToolbarSearchListener(toolbarSearchListener: ToolbarSearchListener?) {
        this.toolbarSearchListener = toolbarSearchListener
    }

    override fun afterTextChanged(s: Editable?) {
        toolbarSearchListener?.onToolbarSearchTextChanged(s?.toString() ?: "")
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}