package com.familyvideo.controllers;

import com.familyvideo.repository.service.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/queries")
public class QueriesController {

    @Autowired
    private JdbcTemplate jdbcTemplate; // JdbcTemplate

    @Autowired
    private ClientRepository clientRepository; // JPA repository (exemplo de uso junto)

    @GetMapping("/actors")
    public List<Map<String, Object>> getActors(@RequestParam("type") int type) {

        String sql = switch (type) {
            case 1 -> "SELECT a.actor_name, a.actor_last_name, COUNT(ma.fk_movie) AS movie_count, GROUP_CONCAT(m.movie_name SEPARATOR ', ') AS movies FROM actors a JOIN movies_actors ma ON a.id = ma.fk_actors JOIN movies m ON ma.fk_movie = m.id GROUP BY a.actor_name, a.actor_last_name HAVING COUNT(ma.fk_movie) > 1 ORDER BY movie_count DESC";
            case 2 -> "SELECT a.actor_name, a.actor_last_name, COUNT(DISTINCT g.id) AS genre_count, GROUP_CONCAT(DISTINCT g.genre_name) AS genres FROM movies_actors ma JOIN actors a ON ma.fk_actors = a.id JOIN movies m ON ma.fk_movie = m.id JOIN genres_movies gm ON m.id = gm.fk_movie JOIN genres g ON gm.fk_genre = g.id GROUP BY a.id, a.actor_name, a.actor_last_name HAVING COUNT(DISTINCT g.id) > 1 ORDER BY genre_count DESC, a.actor_last_name, a.actor_name";
            case 3 -> "SELECT actor_name, actor_last_name, TIMESTAMPDIFF(YEAR, actor_birthday_date, CURDATE()) AS age FROM actors ORDER BY actor_birthday_date ASC LIMIT 1";
            case 4 -> "(SELECT a.actor_name, a.actor_last_name FROM actors a WHERE a.actor_name LIKE 'D%') EXCEPT (SELECT a.actor_name, a.actor_last_name FROM actors a JOIN movies_actors am ON a.id = am.fk_actors JOIN movies m ON am.fk_movie = m.id WHERE m.movie_name LIKE 'H%')";
            default -> "SELECT * FROM actors";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/borrowings")
    public List<Map<String, Object>> getBorrowings(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "SELECT YEAR(b.borrowing_sale_date) AS year, COUNT(be.fk_exemplar) AS total_movies_borrowed FROM borrowings b INNER JOIN borrowing_exemplar be ON b.id = be.fk_borrowing GROUP BY YEAR(b.borrowing_sale_date) ORDER BY total_movies_borrowed DESC LIMIT 1";
            case 2 -> "SELECT g.genre_name, COUNT(be.fk_borrowing) AS unpaid_borrowings FROM genres g INNER JOIN genres_movies gm ON g.id = gm.fk_genre INNER JOIN movies m ON gm.fk_movie = m.id INNER JOIN exemplar e ON m.id = e.fk_movie INNER JOIN borrowing_exemplar be ON e.id = be.fk_exemplar INNER JOIN borrowings b ON be.fk_borrowing = b.id WHERE b.borrowing_is_paid = FALSE GROUP BY g.id ORDER BY unpaid_borrowings DESC";
            case 3 -> "SELECT b.id AS borrowing_id, c.client_name, c.client_last_name, b.borrowing_sale_date, b.borrowing_devolution_date, DATEDIFF(CURRENT_DATE, b.borrowing_devolution_date) AS overdue_days FROM borrowings b JOIN clients c ON b.fk_client = c.id WHERE b.borrowing_is_paid = FALSE ORDER BY overdue_days DESC LIMIT 1";
            default -> "SELECT * FROM borrowings";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/classifications")
    public List<Map<String, Object>> getClassifications(@RequestParam("type") int type) {

        String sql = switch (type) {
            case 1 -> "SELECT c.classification_name AS Classification, COUNT(m.id) AS Quantity_Films FROM classifications c LEFT JOIN movies m ON m.fk_classification = c.id GROUP BY c.classification_name";
            case 2 -> "SELECT c.classification_name AS Classification, COUNT(DISTINCT b.id) AS Quantity_Borrowings FROM borrowings b JOIN borrowing_exemplar be ON b.id = be.fk_borrowing JOIN exemplar e ON be.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id JOIN classifications c ON m.fk_classification = c.id WHERE YEAR(b.borrowing_sale_date) = 2023 GROUP BY c.classification_name ORDER BY Quantity_Borrowings DESC";
            case 3 -> "SELECT c.classification_name AS Classification FROM classifications c WHERE NOT EXISTS (SELECT 1 FROM movies m JOIN genres_movies gm ON m.id = gm.fk_movie WHERE m.fk_classification = c.id GROUP BY m.id HAVING COUNT(gm.fk_genre) > 2)";
            default -> "SELECT * FROM classifications";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/clients")
    public List<Map<String, Object>> getClients(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "SELECT c.client_name FROM clients c JOIN borrowings b ON c.id = b.fk_client WHERE b.borrowing_is_paid = FALSE UNION SELECT c.client_name FROM clients c JOIN borrowings b ON c.id = b.fk_client WHERE YEAR(b.borrowing_sale_date) < 2024";
            case 2 -> "SELECT DISTINCT c.id AS ClientID, CONCAT(c.client_name, ' ', c.client_last_name) AS CLientName FROM clients c WHERE EXISTS (SELECT 1 FROM borrowings b JOIN borrowing_exemplar be ON b.id = be.fk_borrowing JOIN exemplar e ON be.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id JOIN genres_movies gm ON m.id = gm.fk_movie JOIN genres g ON gm.fk_genre = g.id WHERE b.fk_client = c.id AND g.genre_name = 'Science Fiction') AND EXISTS (SELECT 1 FROM borrowings b2 JOIN borrowing_exemplar be2 ON b2.id = be2.fk_borrowing JOIN exemplar e2 ON be2.fk_exemplar = e2.id JOIN movies m2 ON e2.fk_movie = m2.id JOIN directors d ON m2.fk_director = d.id WHERE b2.fk_client = c.id AND d.director_name = 'Christopher' AND d.director_last_name = 'Nolan') ORDER BY ClientName";
            case 3 -> "SELECT c.client_name, c.client_last_name, a.address_street, a.address_number, a.address_city FROM clients c JOIN addresses a ON c.fk_address = a.id JOIN borrowings b ON c.id = b.fk_client WHERE b.borrowing_is_paid = 0 AND c.id NOT IN (SELECT c.id FROM clients c JOIN addresses a ON c.fk_address = a.id JOIN borrowings b ON c.id = b.fk_client WHERE YEAR(b.borrowing_sale_date) = 2024)";
            case 4 -> "SELECT DISTINCT c.id AS ClientID, CONCAT(c.client_name, ' ', c.client_last_name) AS ClientName FROM clients c JOIN borrowings b ON c.id = b.fk_client JOIN borrowing_exemplar be ON b.id = be.fk_borrowing JOIN exemplar e ON be.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id JOIN genres_movies gm ON m.id = gm.fk_movie JOIN genres g ON gm.fk_genre = g.id WHERE g.genre_name = 'Action' AND c.id NOT IN (SELECT c.id FROM clients c JOIN borrowings b ON c.id = b.fk_client JOIN borrowing_exemplar be ON b.id = be.fk_borrowing JOIN exemplar e ON be.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id JOIN genres_movies gm ON m.id = gm.fk_movie JOIN genres g ON gm.fk_genre = g.id WHERE g.genre_name = 'Comedy')";
            default -> "SELECT * FROM clients";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/directors")
    public List<Map<String, Object>> getDirectors(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "SELECT d.director_name, d.director_last_name, s.section_name, s.section_description FROM directors d JOIN movies m ON d.id = m.fk_director JOIN classifications c ON m.fk_classification = c.id JOIN sections s ON s.fk_classification = c.id GROUP BY d.id, s.id ORDER BY d.director_last_name, d.director_name, s.section_name";
            case 2 -> "SELECT DISTINCT d.director_name, d.director_last_name FROM directors d JOIN movies m ON d.id = m.fk_director JOIN exemplar e ON e.fk_movie = m.id JOIN borrowing_exemplar be ON e.id = be.fk_exemplar JOIN borrowings b ON be.fk_borrowing = b.id WHERE d.director_name LIKE 'H%' ORDER BY d.director_name, d.director_last_name";
            case 3 -> "SELECT d.director_name, d.director_last_name, COUNT(be.fk_borrowing) AS total_borrowings FROM directors d INNER JOIN movies m ON d.id = m.fk_director INNER JOIN exemplar e ON m.id = e.fk_movie INNER JOIN borrowing_exemplar be ON e.id = be.fk_exemplar GROUP BY d.id ORDER BY total_borrowings DESC";
            case 4 -> "SELECT d.director_name, d.director_last_name FROM directors d JOIN movies m ON m.fk_director = d.id WHERE m.movie_name LIKE 'L%' AND m.id IN (SELECT m.id FROM movies m WHERE m.movie_release_year = 2016)";
            default -> "SELECT * FROM directors";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/employees")
    public List<Map<String, Object>> getEmployees(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "SELECT e.employee_name FROM employees e WHERE e.employee_salary > ALL (SELECT e2.employee_salary FROM employees e2 WHERE e.id <> e2.id)";
            case 2 -> "(SELECT id, employee_name, employee_last_name FROM employees WHERE fk_section = 1) UNION (SELECT id, employee_name, employee_last_name FROM employees WHERE fk_section = 2)";
            case 3 -> "SELECT id, employee_name, employee_last_name, employee_birthday_date FROM employees WHERE MONTH(employee_birthday_date) IN (12, 1, 2) UNION (SELECT id, employee_name, employee_last_name, employee_birthday_date FROM employees WHERE MONTH(employee_birthday_date) IN (6, 7, 8))";
            default -> "SELECT * FROM employees";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/exemplars")
    public List<Map<String, Object>> getExemplars(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "SELECT e.id FROM borrowings b JOIN borrowing_exemplar be ON b.id = be.fk_borrowing JOIN exemplar e ON be.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id WHERE m.movie_name = 'How to Lose a Guy in 10 Days' AND e.id IN (SELECT e.id FROM borrowings b JOIN borrowing_exemplar be ON b.id = be.fk_borrowing WHERE b.borrowing_is_paid = FALSE) GROUP BY e.id";
            case 2 -> "(SELECT fk_movie, exemplar_buy_date FROM exemplar WHERE exemplar_buy_date BETWEEN '2023-06-01' AND '2023-06-30') UNION (SELECT fk_movie, exemplar_buy_date FROM exemplar WHERE exemplar_buy_date BETWEEN '2023-11-01' AND '2023-11-30')";
            default -> "SELECT * FROM exemplars";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/genres")
    public List<Map<String, Object>> getGenres(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "SELECT g.genre_name, COUNT(CASE WHEN b.borrowing_is_paid = FALSE THEN 1 END) * 100.0 / COUNT(*) AS unpaid_rate FROM genres g JOIN genres_movies gm ON g.id = gm.fk_genre JOIN movies m ON gm.fk_movie = m.id JOIN exemplar e ON e.fk_movie = m.id JOIN borrowing_exemplar be ON e.id = be.fk_exemplar JOIN borrowings b ON be.fk_borrowing = b.id GROUP BY g.id, g.genre_name ORDER BY unpaid_rate DESC";
            case 2 -> "SELECT c.client_name AS ClientName, c.client_last_name AS ClientLastName, GROUP_CONCAT(DISTINCT g.genre_name SEPARATOR ', ') AS Genres, COUNT(DISTINCT g.genre_name) AS GenreCount FROM borrowings b JOIN borrowing_exemplar be ON b.id = be.fk_borrowing JOIN exemplar e ON be.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id JOIN genres_movies gm ON m.id = gm.fk_movie JOIN genres g ON gm.fk_genre = g.id JOIN clients c ON b.fk_client = c.id GROUP BY c.id HAVING COUNT(DISTINCT g.genre_name) > 1";
            case 3 -> "SELECT g.genre_name AS Genre, COUNT(be.fk_exemplar) AS Rentals FROM genres g JOIN genres_movies gm ON g.id = gm.fk_genre JOIN movies m ON gm.fk_movie = m.id JOIN exemplar e ON m.id = e.fk_movie JOIN borrowing_exemplar be ON e.id = be.fk_exemplar GROUP BY g.id ORDER BY Rentals ASC LIMIT 3";
            case 4 -> "SELECT g.genre_name, COUNT(be.fk_borrowing) AS total_borrowings FROM genres g INNER JOIN genres_movies gm ON g.id = gm.fk_genre INNER JOIN movies m ON gm.fk_movie = m.id INNER JOIN exemplar e ON m.id = e.fk_movie INNER JOIN borrowing_exemplar be ON e.id = be.fk_exemplar GROUP BY g.id ORDER BY total_borrowings DESC LIMIT 1";
            default -> "SELECT * FROM genres";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/movies")
    public List<Map<String, Object>> getMovies(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "SELECT DISTINCT m.movie_name AS MovieName FROM movies m JOIN movies_actors ma ON m.id = ma.fk_movie JOIN actors a ON ma.fk_actors = a.id WHERE TIMESTAMPDIFF(YEAR, a.actor_birthday_date, CURDATE()) < 25 ORDER BY MovieName ASC";
            case 2 -> "SELECT m.movie_name, COUNT(m.id) as number_borrowings FROM borrowings b JOIN borrowing_exemplar be ON b.id = be.fk_borrowing JOIN exemplar e ON be.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id WHERE YEAR(b.borrowing_sale_date) = 2023 GROUP BY m.id";
            case 3 -> "SELECT m.movie_name AS MovieName, COUNT(b.id) AS Borrings FROM movies m JOIN classifications c ON m.fk_classification = c.id JOIN exemplar e ON m.id = e.fk_movie JOIN borrowing_exemplar be ON e.id = be.fk_exemplar JOIN borrowings b ON be.fk_borrowing = b.id WHERE c.classification_age > 12 GROUP BY m.id, m.movie_name ORDER BY Borrings DESC";
            case 4 -> "SELECT m.movie_name, GROUP_CONCAT(g.genre_name) AS genres, c.classification_name FROM movies m JOIN genres_movies gm ON gm.fk_movie = m.id JOIN genres g ON gm.fk_genre = g.id JOIN classifications c ON m.fk_classification = c.id GROUP BY m.id, c.id ORDER BY m.movie_name";
            case 5 -> "SELECT m.movie_name, COUNT(e.id) AS total_exemplars, (SELECT COUNT(g.id) FROM genres_movies gm JOIN genres g ON gm.fk_genre = g.id WHERE gm.fk_movie = m.id) AS total_genres FROM movies m JOIN exemplar e ON m.id = e.fk_movie GROUP BY m.id HAVING COUNT(e.id) > SOME (SELECT COUNT(e2.id) FROM exemplar e2 GROUP BY e2.fk_movie) AND (SELECT COUNT(g.id) FROM genres_movies gm JOIN genres g ON gm.fk_genre = g.id WHERE gm.fk_movie = m.id) > 1";
            default -> "SELECT * FROM movies";
        };

        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/sections")
    public List<Map<String, Object>> getSections(@RequestParam("type") int type) {
        String sql = switch (type) {
            case 1 -> "(SELECT s.section_name FROM sections s JOIN classifications c ON s.fk_classification = c.id JOIN movies m ON m.fk_classification = c.id GROUP BY s.id HAVING COUNT(m.id) > ALL (SELECT COUNT(m.id) FROM sections s2 JOIN classifications c ON s2.fk_classification = c.id JOIN movies m ON m.fk_classification = c.id WHERE s2.id <> s.id GROUP BY s2.id)) UNION (SELECT s.section_name FROM borrowing_exemplar b JOIN exemplar e ON b.fk_exemplar = e.id JOIN movies m ON e.fk_movie = m.id JOIN classifications c ON c.id = m.fk_classification JOIN sections s ON s.fk_classification = c.id GROUP BY s.id, s.section_name HAVING COUNT(b.fk_borrowing) > ALL (SELECT COUNT(b2.fk_borrowing) FROM borrowing_exemplar b2 JOIN exemplar e2 ON b2.fk_exemplar = e2.id JOIN movies m2 ON e2.fk_movie = m2.id JOIN classifications c2 ON c2.id = m2.fk_classification JOIN sections s2 ON s2.fk_classification = c2.id WHERE s2.id <> s.id GROUP BY s2.id))";
            case 2 -> "SELECT g.genre_name FROM sections s JOIN classifications c ON s.fk_classification = c.id JOIN movies m ON c.id = m.fk_classification JOIN genres_movies gm ON m.id = gm.fk_movie JOIN genres g ON gm.fk_genre = g.id WHERE s.section_name = 'Afternoon Favorites' GROUP BY g.id ORDER BY COUNT(g.id)";
            case 3 -> "SELECT s.section_name, COUNT(e.id) AS total_employees FROM sections s JOIN employees e ON s.id = e.fk_section GROUP BY s.section_name HAVING COUNT(e.id) >= ALL (SELECT COUNT(e2.id) FROM sections s2 JOIN employees e2 ON s2.id = e2.fk_section GROUP BY s2.section_name)";
            default -> "SELECT * FROM sections";
        };

        return jdbcTemplate.queryForList(sql);
    }
}