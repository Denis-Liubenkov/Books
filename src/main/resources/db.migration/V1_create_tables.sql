CREATE TABLE Authors (
                         author_id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         biography TEXT
);
CREATE TABLE Genres (
                        genre_id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);
CREATE TABLE Books (
                       book_id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       publication_date DATE,
                       author_id INTEGER NOT NULL,
                       genre_id INTEGER NOT NULL,
                       FOREIGN KEY (author_id) REFERENCES Authors(author_id),
                       FOREIGN KEY (genre_id) REFERENCES Genres(genre_id)
);
CREATE TABLE Publishers (
                            publisher_id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);
CREATE TABLE Book_Publisher (
                                book_id INTEGER NOT NULL,
                                publisher_id INTEGER NOT NULL,
                                PRIMARY KEY (book_id, publisher_id),
                                FOREIGN KEY (book_id) REFERENCES Books(book_id),
                                FOREIGN KEY (publisher_id) REFERENCES Publishers(publisher_id)
);