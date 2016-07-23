package xyz.triviopoly.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Question;

public class CqadDao {
	private static CqadDao instance;

	private static final String CONNECT_URL = "jdbc:hsqldb:file:db/triviopoly;shutdown=true";
	private static final String USERNAME = "SA";
	private static final String PASSWORD = "";

	private static final String CATEGORY_COUNT_SELECT = "select count(*) from categories";

	private static final String CATEGORY_NAME_SELECT = "select category_name from categories where category_id = ?";

	private static final String CATEGORY_QUESTIONS_SELECT = "select question, question_type, answer, difficulty from questions where category_id = ? order by difficulty";

	private CqadDao() {
		try {
			// Loading Driver for hsqldb
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static CqadDao getInstance() {
		if (instance == null) {
			instance = new CqadDao();
		}
		return instance;
	}

	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection(CONNECT_URL, USERNAME, PASSWORD);
	}

	public List<Category> getGameboardCategories(int numberOfCategories,
			List<Category> categoriesToExclude) {
		Connection c = null;
		try {
			c = createConnection();
			int totalNumberOfCategories = getTotalNumberOfCategories(c);
			List<Integer> randomCategoryIds = getRandomCategoryIds(
					totalNumberOfCategories, numberOfCategories,
					categoriesToExclude);
			return queryCategories(randomCategoryIds, c);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int getTotalNumberOfCategories(Connection c) throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = c.prepareStatement(CATEGORY_COUNT_SELECT);
			rs = s.executeQuery();
			rs.next();
			return rs.getInt(1);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<Integer> getRandomCategoryIds(int totalNumberOfCategories,
			int numberOfCategories, List<Category> categoriesToExclude) {
		HashSet<Integer> alreadyUsedCategories = new HashSet<>();
		if (categoriesToExclude != null) {
			for (Category category : categoriesToExclude) {
				alreadyUsedCategories.add(category.getId());
			}
		}
		List<Integer> randomCategoryIds = new ArrayList<>();
		Random random = new Random();
		while (randomCategoryIds.size() < numberOfCategories) {
			int randomId = random.nextInt(totalNumberOfCategories) + 1;
			if (!alreadyUsedCategories.contains(randomId)) {
				randomCategoryIds.add(randomId);
				alreadyUsedCategories.add(randomId);
			}
		}
		return randomCategoryIds;
	}

	private List<Category> queryCategories(List<Integer> categoryIds,
			Connection c) throws SQLException {
		List<Category> categories = new ArrayList<>();
		for (int categoryId : categoryIds) {
			categories.add(queryCategory(categoryId, c));
		}
		return categories;
	}

	private Category queryCategory(int categoryId, Connection c)
			throws SQLException {
		String name = queryCategoryName(categoryId, c);
		List<Question> questions = queryQuestions(categoryId, c);
		return new Category(categoryId, name, questions);
	}

	private String queryCategoryName(int categoryId, Connection c)
			throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = c.prepareStatement(CATEGORY_NAME_SELECT);
			s.setInt(1, categoryId);
			rs = s.executeQuery();
			rs.next();
			return rs.getString(1);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<Question> queryQuestions(int categoryId, Connection c)
			throws SQLException {
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = c.prepareStatement(CATEGORY_QUESTIONS_SELECT);
			s.setInt(1, categoryId);
			rs = s.executeQuery();
			List<Question> questions = new ArrayList<>();
			while (rs.next()) {
				questions.add(createQuestion(rs));
			}
			return questions;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Question createQuestion(ResultSet rs) throws SQLException {
		return new Question(rs.getString(1), rs.getString(2), rs.getString(3),
				rs.getInt(4));
	}

	public static void main(String[] args) throws Exception {
		CqadDao dao = CqadDao.getInstance();
		List<Category> categories = dao.getGameboardCategories(6, null);
		for (Category category : categories) {
			System.out.println(category.getName());
		}
	}

}
