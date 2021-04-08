package com.wdz.ktcommon.http.response

data class ProjectResponse(var children: List<String>,
                           var courseId: Int,
                           var id: Int,
                           var name: String,
                           var order: Int,
                           var parentChapterId: Int,
                           var userControlSetTop: Boolean,
                           var visible: Int) {
}