package com.wdz.ktcommon.http.response

class NaviResponse(var data: List<Data>) {


    data class Data(var articles: List<Articles>) {


        data class Articles(
            var apkLink: String,
            var audit: Int,
            var author: String,
            var canEdit: Boolean,
            var chapterId: Int,
            var chapterName: String,
            var collect: Boolean,
            var courseId: Int,
            var desc: String,
            var descMd: String,
            var envelopePic: String,
            var fresh: Boolean,
            var id: Int,
            var link: String,
            var niceDate: String,
            var niceShareDate: String,
            var origin: String,
            var prefix: String,
            var projectLink: String,
            var publishTime: Int,
            var realSuperChapterId: Int,
            var selfVisible: Int,
            var shareDate: Int,
            var shareUser: String,
            var superChapterId: Int,
            var superChapterName: String,
            var tags: List<String>,
            var title: String,
            var type: Int,
            var userId: Int,
            var visible: Int,
            var zan: Int = 0
        ) {


        }
    }
}