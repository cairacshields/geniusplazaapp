package com.example.geniusplazaapp.objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("email")
        @Expose
        private String email;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
}
