# Nocturnal

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Schema](#Schema)

## Overview
### Description
Social media application like Twitter where users can post and see people's stories.

### App Evaluation
- **Category:** Social Networking
- **Mobile:** This app would be primarily developed for Android. In the future, this app could be brought to the web.  
- **Story:** Users can post stories. Users can also follow other users to view, like, and comment on their their stories.
- **Market:** Any individual could choose to use this app.
- **Habit:** This app could be used as often or unoften as the user wanted depending on how deep their social life is, and what exactly they're looking for.

## Product Spec
### 1. User Stories

**Required Stories**

* Sign up for an account
* User logs in to their account and can see their home page
* Post stories
* Follow other users
* View, like, and comment on stories
* Profile page for each user
* Settings (Accesibility, Notification, General, etc.)
* Log out

- [x] sign in for account
- [x] home page is visible
- [x] capable of creating and posting
- [x] capable of following others
- [x] able to see profile pages both yours and others
- [x] setting page
- [x] log out

**Optional Nice-to-have Stories**
* Chat with other users via direct messages

### 2. Screen Archetypes

* Login 
* Register - User signs up or logs into their account
* Home Screen - View stories from other users
   * User can like and comment on each story
* Profile Screen 
   * Allows user to upload a photo and fill in information that is interesting to them and others
* Settings Screen
   * Lets users change settings

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* Profile
* Create post
* Settings

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Home -> List of stories
* Create post -> Text field to be modified
* Profile -> Text field to be modified
* Settings -> Toggle settings

## Mockups

## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| image author |
   | image         | File     | image that user posts |
   | body          | String   | post body |
   | commentsCount | Number   | number of comments that has been posted to an image |
   | likesCount    | Number   | number of likes for the post |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query all posts where user is author
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
      - (Create/POST) Create a new comment on a post
      - (Delete) Delete existing comment
   - Create Post Screen
      - (Create/POST) Create a new post object
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Update/PUT) Update user profile image
