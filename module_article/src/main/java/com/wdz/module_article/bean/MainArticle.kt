package com.wdz.module_article.bean

/**
 * @Author dezhi.wang
 * @Date 2021/1/18 16:16
 */
data class MainArticle(
    var link: String,
    var chapterName: String,
    var niceShareDate: String,
    var title: String,
    var envelopePic: String,
    var des: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        //instanceof比较判断是否是同一类或者子父类关系
        if (other !is MainArticle) {
            return false
        }
        return link == other.link && chapterName == other.chapterName && niceShareDate == other.niceShareDate && title == other.title && envelopePic == other.envelopePic && des == other.des
    }

    override fun hashCode(): Int {
        var result = link.hashCode() ?: 0
        result = 31 * result + (chapterName.hashCode() ?: 0)
        result = 31 * result + (niceShareDate.hashCode() ?: 0)
        result = 31 * result + (title.hashCode() ?: 0)
        result = 31 * result + (envelopePic.hashCode() ?: 0)
        result = 31 * result + (des.hashCode() ?: 0)
        return result
    }
}