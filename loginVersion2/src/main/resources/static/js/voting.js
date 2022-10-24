// array to store current voting objects
let current = [
    {
        q: "gif",
        title: "GIF vs GIF",
        description: "How is it pronounced?",
        candidate1: {
            name: "GIF - Soft G like 'Gift'",
            img: "imgs/gif.jpg"
        },
        candidate2:  {
            name: "GIF - Hard G like 'Giraffe'",
            img: "imgs/jif.png"
        }
    },
    {
        q: "cereal",
        title: "Which is first: Milk or Cereal",
        description: "What goes in the bowl first?",
        candidate1: {
            name: "Milk",
            img: "imgs/milk.jpg",
        },
        candidate2:  {
            name: "Cereal",
            img: "imgs/cereal.jpg",
        }
    },
    {
        q: "ford_vs_chevy",
        title: "Ford vs Chevy",
        description: "Which brand is best?",
        candidate1: {
            name: "Ford",
            img: "imgs/ford.png",
        },
        candidate2:  {
            name: "Chevy",
            img: "imgs/chevy.jpg",
        }
    },
    {
        q: "apple_cherry",
        title: "Apples vs Cherries",
        description: "What is your favorite fruit?",
        candidate1: {
            name: "Apples",
            img: "imgs/apples.jpg",
        },
        candidate2:  {
            name: "Cherries",
            img: "imgs/cherries.jpg",
        }
    },
    {
        q: "apple_samsung",
        title: "Apple vs Samsung",
        description: "Which is the best phone company?",
        candidate1: {
            name: "Apple",
            img: "imgs/apple.png",
        },
        candidate2:  {
            name: "Samsung",
            img: "imgs/samsung.png",
        }
    }
]


/**
 * @description gets vote info from current and displays it
 * 
 * @param {string} query - query word of this result
 */
 function displayVote (query) {
    for (vote of current) {
        if (vote.q === query) {
            // display title and description
            printTitle(vote.title);
            printDescription(vote.description);
            // set candidate 1 info
            populateCandidate(1, vote.candidate1.name, vote.candidate1.img);
            // set candidate 2 info
            populateCandidate(2, vote.candidate2.name, vote.candidate2.img);
        } 
    }
}

// once the document is loaded
document.addEventListener('DOMContentLoaded', function () {
    // how to get query word from the url
    const CURRENT_URL = new URL(window.location.href);
    const QUERY = CURRENT_URL.searchParams.get("q");
    // print vote
    displayVote(QUERY);
});