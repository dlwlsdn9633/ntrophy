// 헤더 검색창
document.addEventListener("DOMContentLoaded", () => {
  const searchBtn = document.getElementById("searchBtn");
  const searchPopup = document.getElementById("searchPopup");
  const searchClose = document.getElementById("searchClose");
  const searchInput = document.getElementById("searchInput");

  if (searchBtn && searchPopup && searchClose && searchInput) {
    searchBtn.addEventListener("click", (e) => {
      e.preventDefault();
      searchPopup.classList.toggle("show");
      if (searchPopup.classList.contains("show")) {
        searchInput.focus();
      } else {
        searchInput.value = "";
      }
    });

    searchClose.addEventListener("click", () => {
      searchPopup.classList.remove("show");
      searchInput.value = "";
    });

    document.addEventListener("click", (e) => {
      if (
        !searchPopup.contains(e.target) &&
        !searchBtn.contains(e.target)
      ) {
        searchPopup.classList.remove("show");
        searchInput.value = "";
      }
    });
  }
});