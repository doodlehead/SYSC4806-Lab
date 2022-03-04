let addressBooks = [];
const host = 'whispering-lowlands-82355.herokuapp.com'

// Render according to locally stored addressBooks var
function renderAddressBooks() {
    let root = $("#address-books");
    // Clear contents
    root.html("");
    // Render new
    addressBooks.forEach(elem => {
        root.append(
            `<div style="display: flex; flex-direction: column; align-items: center; margin: 20px 0; border: 1px solid black; padding: 12px">
                <span class="debug">Address Book: ${JSON.stringify(elem)}</span>
                <button id="b-${elem.id}" class="createBuddy">add buddy here</button>
                ${renderAddressBook(elem)}
            </div>`
        )
    });

    $(".createBuddy").click(function() {
        let bookId = $(this).attr('id').split('-')[1];
        createBuddy(bookId);
    });

    $(".deleteBuddy").click(function() {
        let tokens = $(this).attr('id').split('-');
        const bookId = tokens[1];
        const buddyId = tokens[2];
        deleteBuddy(bookId, buddyId);
    });

    $('.debug').hide();
}

// Return HTML representation of address book
function renderAddressBook(elem) {
    let buddies = '';
    elem.buddies.forEach(buddy => {
        buddies += `<div>${buddy.id}, ${buddy.name}, ${buddy.address}, ${buddy.phoneNumber} <button id="db-${elem.id}-${buddy.id}" class="deleteBuddy">Delete</button></div>`
    });

    return (
        `<div style="display: flex; flex-direction: column; align-items: center; margin: 20px 0">
            ${buddies}
        </div>`
    );
}

function deleteBuddy(bookId, buddyId) {
    $.ajax({
        type: "DELETE",
        url: `https://${host}/api/book/${bookId}/buddy/${buddyId}`
    }).then(data => {
        // update data + rerender
        getAddressBooks();
    });
}

function createBuddy(bookId) {
    $.ajax({
        type: "POST",
        url: `https://${host}/api/book/${bookId}/buddy`,
        data: JSON.stringify({
            name: $('#name').val(),
            address: $('#address').val(),
            phoneNumber: $('#phone').val()
        }),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
    }).then(data => {
        // Clear input fields
        $('#name').val('')
        $('#address').val('')
        $('#phone').val('')
        // update data + rerender
        getAddressBooks();
    });
}

function getAddressBooks() {
    $.ajax({
        url: "https://${host}/api/book/"
    }).then(data => {
        addressBooks = data;
        renderAddressBooks();
    });
}

function createAddressBook() {
    $.ajax({
        type: "POST",
        url: "https://${host}/api/book/"
    }).then(data => {
        addressBooks.push(data);
        // Update UI
        renderAddressBooks();
    });
}

$(document).ready(function() {
    $("#create").click(createAddressBook);
    $("show-debug").click(function () {
        $('.debug').show();
    })

    getAddressBooks();
});