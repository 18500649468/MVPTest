package com.example.ll.mvp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class BookInfo implements Serializable{

    /**
     * date : 20170804
     * stories : [{"ga_prefix":"080411","id":9556033,"images":["https://pic2.zhimg.com/v2-13d6b9e76744c91150fac5cf50219dad.jpg"],"title":"中国 DOTA 能否继续屹立在世界之巅，就看今年的 TI7 了","type":0},{"ga_prefix":"080410","id":9553505,"images":["https://pic1.zhimg.com/v2-8422dac8297db5ac9f92f03424c32e74.jpg"],"title":"做联合国的和平大使有一阵子了，我的感受是这样","type":0},{"ga_prefix":"080409","id":9552034,"images":["https://pic3.zhimg.com/v2-ac9f90e33563e55695b54d5d784c3af6.jpg"],"title":"听上去无限美好的「无现金社会」，把那些走不快的人忘了","type":0},{"ga_prefix":"080408","id":9554063,"images":["https://pic1.zhimg.com/v2-58c5b650843f952d085f7c37d485b11c.jpg"],"multipic":true,"title":"为什么现代人很热衷摩天大楼这样的设计？","type":0},{"ga_prefix":"080407","id":9555648,"images":["https://pic2.zhimg.com/v2-0f60f60cb9fdd45ce15ab7b7959b16e9.jpg"],"title":"2.2 亿的破纪录解约费，内马尔离开巴萨去了巴黎","type":0},{"ga_prefix":"080407","id":9553547,"images":["https://pic4.zhimg.com/v2-b1e03a97674f29331792f7935b9da72b.jpg"],"title":"日本动画没钱要完？这家企业笑了：咱最不缺的就是钱","type":0},{"ga_prefix":"080407","id":9555238,"images":["https://pic2.zhimg.com/v2-d5d4fa628033ef3ecde84bf1c74e1701.jpg"],"title":"每次看到国产手机说要秒杀单反，我心里只有「呵呵」","type":0},{"ga_prefix":"080406","id":9554001,"images":["https://pic2.zhimg.com/v2-38ea05afbec642651e6f42fcb2768b9d.jpg"],"title":"瞎扯 · 如何正确地吐槽","type":0}]
     * top_stories : [{"ga_prefix":"080411","id":9556033,"image":"https://pic1.zhimg.com/v2-cdcd66f6a8b451b4d1640e6b008993b0.jpg","title":"中国 DOTA 能否继续屹立在世界之巅，就看今年的 TI7 了","type":0},{"ga_prefix":"080407","id":9555648,"image":"https://pic2.zhimg.com/v2-a9701d83a2eab9229e56470f1ea6bef5.jpg","title":"2.2 亿的破纪录解约费，内马尔离开巴萨去了巴黎","type":0},{"ga_prefix":"080407","id":9553547,"image":"https://pic1.zhimg.com/v2-9e873e6ba45d4dd58243a3a61956de70.jpg","title":"日本动画没钱要完？这家企业笑了：咱最不缺的就是钱","type":0},{"ga_prefix":"080407","id":9555238,"image":"https://pic3.zhimg.com/v2-53ab404e19016645dc0bd92bf4c66aea.jpg","title":"每次看到国产手机说要秒杀单反，我心里只有「呵呵」","type":0},{"ga_prefix":"080317","id":9554946,"image":"https://pic4.zhimg.com/v2-ac99139a24f2301d24bed18175b40b9b.jpg","title":"为什么《战狼 2》的票房这么高？","type":0}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean implements Serializable{
        /**
         * ga_prefix : 080411
         * id : 9556033
         * images : ["https://pic2.zhimg.com/v2-13d6b9e76744c91150fac5cf50219dad.jpg"]
         * title : 中国 DOTA 能否继续屹立在世界之巅，就看今年的 TI7 了
         * type : 0
         * multipic : true
         */

        private String ga_prefix;
        private int id;
        private String title;
        private int type;
        private boolean multipic;
        private List<String> images;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * ga_prefix : 080411
         * id : 9556033
         * image : https://pic1.zhimg.com/v2-cdcd66f6a8b451b4d1640e6b008993b0.jpg
         * title : 中国 DOTA 能否继续屹立在世界之巅，就看今年的 TI7 了
         * type : 0
         */

        private String ga_prefix;
        private int id;
        private String image;
        private String title;
        private int type;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
