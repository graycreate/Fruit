package me.ghui.fruit;

import me.ghui.fruit.annotations.Pick;

import java.util.List;

/**
 * Created by ghui on 05/05/2017.
 */
@Pick("div.box")
public class NewsInfo {
    @Pick("div.cell.item")
    private List<NewsItem> items;

    public List<NewsItem> getItems() {
        return items;
    }
    public void setItems(List<NewsItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "items=" + items +
                '}';
    }

    public static class NewsItem {
        @Pick(value = "span.item_title > a")
        private String title;
        @Pick(value = "span.item_title > a", attr = "href")
        private String linkPath;
        @Pick(value = "td > a > img", attr = "src")
        private String avatar;
        @Pick(value = "td > a", attr = "href")
        private String avatarLink;
        @Pick(value = "span.small.fade > strong > a")
        private String user;
        @Pick(value = "span.small.fade", attr = "ownText")
        private String time;
        @Pick(value = "span.small.fade > a")
        private String tagName;
        @Pick(value = "span.small.fade > a", attr = "href")
        private String tagLink;
        @Pick(value = "a.count_livid")
        private int replies;

        public int getReplies() {
            return replies;
        }

        public void setReplies(int replies) {
            this.replies = replies;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLinkPath() {
            return linkPath;
        }

        public String getTopicId() {
            // /t/358992#reply458
            return linkPath.substring(3, linkPath.indexOf("#"));
        }

        public void setLinkPath(String linkPath) {
            this.linkPath = linkPath;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatarLink() {
            return avatarLink;
        }

        public void setAvatarLink(String avatarLink) {
            this.avatarLink = avatarLink;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getTime() {
            //  •  1 小时 48 分钟前  •  最后回复来自
            if (time != null && time.length() > 0) {
                String[] strs = time.split("•");
                if (strs.length >= 3) {
                    time = strs[2].replaceAll("\\u00A0", "").trim();
                } else {
                    time = null;
                }
            }
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getTagLink() {
            return tagLink;
        }

        public void setTagLink(String tagLink) {
            this.tagLink = tagLink;
        }

        @Override
        public String toString() {
            return "NewsItem{" +
                    "title='" + title + '\'' +
                    ", linkPath='" + linkPath + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", avatarLink='" + avatarLink + '\'' +
                    ", user='" + user + '\'' +
                    ", time='" + time + '\'' +
                    ", tagName='" + tagName + '\'' +
                    ", tagLink='" + tagLink + '\'' +
                    ", replies=" + replies +
                    '}';
        }
    }
}
