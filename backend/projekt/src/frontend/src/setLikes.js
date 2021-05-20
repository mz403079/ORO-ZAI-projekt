export default function (posts) {
  if (!localStorage.user) {
    return;
  }
  let user = JSON.parse(localStorage.user);
  for (let i = 0; i < posts.length; i++) {
    let likes = posts[i].likes;
    for (let j = 0; j < likes.length; j++) {
      if (likes[j].userId === user.id) {
        posts[i].likes = true
        break;
      }
    }
    if (posts[i].likes !== true) {
      posts[i].likes = false
    }
  }
}