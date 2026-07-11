document.addEventListener("DOMContentLoaded", function () {
	const large = document.getElementById("largeCategoryId");
	const middle = document.getElementById("middleCategoryId");
	const small = document.getElementById("smallCategoryId");

	const middleOptions = Array.from(middle.options);
	const smallOptions = Array.from(small.options);

	large.addEventListener("change", function () {
		const largeId = large.value;

		middle.innerHTML = "";
		small.innerHTML = "";

		middleOptions.forEach(option => {
			if (option.value === "" || option.dataset.largeId === largeId || largeId === "") {
				middle.appendChild(option.cloneNode(true));
			}
		});

		smallOptions.forEach(option => {
			if (option.value === "" || option.dataset.largeId === largeId || largeId === "") {
				small.appendChild(option.cloneNode(true));
			}
		});
	});

	middle.addEventListener("change", function () {
		const selected = middle.options[middle.selectedIndex];
		const middleId = middle.value;
		const largeId = selected.dataset.largeId;

		if (largeId) {
			large.value = largeId;
		}

		small.innerHTML = "";

		smallOptions.forEach(option => {
			if (option.value === "" || option.dataset.middleId === middleId || middleId === "") {
				small.appendChild(option.cloneNode(true));
			}
		});
	});

	small.addEventListener("change", function () {
	    const selected = small.options[small.selectedIndex];

	    if (!selected.value) {
	        return;
	    }

	    const middleId = selected.dataset.middleId;
	    const largeId = selected.dataset.largeId;

	    large.value = largeId;

	    middle.innerHTML = "";

	    middleOptions.forEach(option => {
	        if (option.value === "" || option.dataset.largeId === largeId) {
	            middle.appendChild(option.cloneNode(true));
	        }
	    });

	    middle.value = middleId;
	});
});