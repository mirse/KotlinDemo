package com.wdz.ktcommon.http.response

data class ShareArticleResponse(var data: Data) {


    data class Data(
        var coinInfo: CoinInfo,
        var shareArticles: ShareArticles
    ) {


        data class ShareArticles(
            var curPage: Int,
            var datas: List<Datas>,
            var offset: Int,
            var over: Boolean,
            var pageCount: Int,
            var size: Int,
            var total: Int,
        ) {

            data class Datas(
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
                var host: String,
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
                var zan: Int
            ) {

            }
        }

        data class CoinInfo(
            var coinCount: Int,
            var level: Int,
            var nickname: String,
            var rank: String,
            var userId: Int,
            var username: String
        ) {

        }
    }
}