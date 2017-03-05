package by.tsvrko.manics.model.dataimport;

import java.io.Serializable;

    /**
     * Created main.main.java.by tsvrko on 1/25/2017.
     */
    public class ChatInfo implements Serializable {

        private long chatId;
        private String title;
        private long lastMessageDate;
        private int userCount;
        private String image50;
        private String image100;
        private String image200;

        public long getChatId() {
            return chatId;
        }

        public void setChatId(long chatId) {
            this.chatId = chatId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getLastMessageDate() {
            return lastMessageDate;
        }

        public void setLastMessageDate(long lastMessageDate) {
            this.lastMessageDate = lastMessageDate;
        }

        public int getUserCount() {
            return userCount;
        }

        public void setUserCount(int userCount) {
            this.userCount = userCount;
        }

        public String getImage50() {
            return image50;
        }

        public void setImage50(String image50) {
            this.image50 = image50;
        }

        public String getImage100() {
            return image100;
        }

        public void setImage100(String image100) {
            this.image100 = image100;
        }

        public String getImage200() {
            return image200;
        }

        public void setImage200(String image200) {
            this.image200 = image200;
        }

        public ChatInfo(long chatId, String title, long lastMessageDate, int userCount, String image50, String image100, String image200) {
            this.chatId = chatId;
            this.title = title;
            this.lastMessageDate = lastMessageDate;
            this.userCount = userCount;
            this.image50 = image50;
            this.image100 = image100;
            this.image200 = image200;
        }

        public ChatInfo() {
        }
    }