INSERT INTO `Author` (`id`, `name`) VALUES
  (1, 'Bruce Wayne'),
  (2, 'Tony Stark'),
  (3, 'Dr. Bruce Banner');

INSERT INTO `Book` (`id`, `title`, `genre`) VALUES
  (1, 'How to be Baller Rich', 'Fantasy'),
  (2, 'How to Fight Crazy Bad Dudes', 'Biography'),
  (3, 'Mastering Narcissism', 'Self Help'),
  (4, 'Controlling Rage', 'Self Help');

INSERT INTO `Book_Author` (`books_id`, `authors_id`) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (3, 2),
  (4, 3);
