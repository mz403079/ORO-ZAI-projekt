export default function userIsAdmin() {
  if(localStorage.user) {
    let json = JSON.parse(localStorage.user);
    return json.roles.includes("ROLE_ADMIN");
  }

  else
    return false;
}