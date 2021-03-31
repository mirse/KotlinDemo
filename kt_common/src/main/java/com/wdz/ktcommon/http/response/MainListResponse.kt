package com.wdz.ktcommon.http.response

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 10:58

 */
class MainListResponse {
    var curPage = 0
    var datas: List<Datas> = mutableListOf()
    var offset = 0
    var over = false
    var pageCount = 0
    var size = 0
    var total = 0

    class Datas {
        var apkLink: String? = null
        var audit = 0
        var author: String? = null
        var canEdit = false
        var chapterId = 0
        var chapterName: String? = null
        var collect = false
        var courseId = 0
        var desc: String? = null
        var descMd: String? = null
        var envelopePic: String? = null
        var fresh = false
        var id = 0
        var link: String? = null
        var niceDate: String? = null
        var niceShareDate: String? = null
        var origin: String? = null
        var prefix: String? = null
        var projectLink: String? = null
        var publishTime: Long = 0
        var realSuperChapterId = 0
        var selfVisible = 0
        var shareDate: Long = 0
        var shareUser: String? = null
        var superChapterId = 0
        var superChapterName: String? = null
        var tags: List<Tag>? = null
        var title: String? = null
        var type = 0
        var userId = 0
        var visible = 0
        var zan = 0

        inner class Tag {
            var tag: String? = null
            override fun toString(): String {
                return "Tag{" +
                        "tag='" + tag + '\'' +
                        '}'
            }
        }

        override fun toString(): String {
            return "Datas{" +
                    "apkLink='" + apkLink + '\'' +
                    ", audit=" + audit +
                    ", author='" + author + '\'' +
                    ", canEdit=" + canEdit +
                    ", chapterId=" + chapterId +
                    ", chapterName='" + chapterName + '\'' +
                    ", collect=" + collect +
                    ", courseId=" + courseId +
                    ", desc='" + desc + '\'' +
                    ", descMd='" + descMd + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", fresh=" + fresh +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", niceDate='" + niceDate + '\'' +
                    ", niceShareDate='" + niceShareDate + '\'' +
                    ", origin='" + origin + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", projectLink='" + projectLink + '\'' +
                    ", publishTime=" + publishTime +
                    ", realSuperChapterId=" + realSuperChapterId +
                    ", selfVisible=" + selfVisible +
                    ", shareDate=" + shareDate +
                    ", shareUser='" + shareUser + '\'' +
                    ", superChapterId=" + superChapterId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", tags=" + tags +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", visible=" + visible +
                    ", zan=" + zan +
                    '}'
        }
    }

}