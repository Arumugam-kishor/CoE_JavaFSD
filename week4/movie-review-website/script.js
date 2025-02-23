document.addEventListener('DOMContentLoaded', () => {
    const searchForm = document.getElementById('search-form');
    const searchInput = document.getElementById('search-input');
    const resultsContainer = document.getElementById('results-container');
    const reviewForm = document.getElementById('review-form');
    const reviewsList = document.getElementById('reviews-list');

    if (searchForm) {
        searchForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const query = searchInput.value.trim();
            if (query) {
                fetchMovies(query);
            }
        });
    }

    if (reviewForm) {
        reviewForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const movieTitle = document.getElementById('movie-title').value.trim();
            const reviewText = document.getElementById('review-text').value.trim();
            if (movieTitle && reviewText) {
                addReview(movieTitle, reviewText);
            }
        });
    }

    function fetchMovies(query) {
        const movies = [
            { title: 'Movie 1', synopsis: 'Review' }
        ];
        displayResults(movies);
    }

    function displayResults(movies) {
        resultsContainer.innerHTML = '';
        if (movies.length === 0) {
            resultsContainer.innerHTML = '<p>No movies found.</p>';
            return;
        }

        movies.forEach(movie => {
            const movieElement = document.createElement('div');
            movieElement.innerHTML = `
                <h3>${movie.title}</h3>
                <p>${movie.synopsis}</p>
            `;
            resultsContainer.appendChild(movieElement);
        });
    }

    function addReview(movieTitle, reviewText) {
        const reviewElement = document.createElement('li');
        reviewElement.textContent = `${movieTitle}: ${reviewText}`;
        reviewsList.appendChild(reviewElement);
    }
});