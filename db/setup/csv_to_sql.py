from __future__ import print_function
from csv import reader

CATEGORY_COLUMN = 0
QUESTION_COLUMN = 1
QUESTION_TYPE_COLUMN = 2
ANSWER_COLUMN = 3
DIFFICULTY_COLUMN = 4

questions = []

with open('Jeopardy Questions - Questions.csv', 'r') as csvfile:
    spamreader = reader(csvfile, delimiter=',', quotechar='"')
    questions = [row for row in spamreader]

del questions[0]

category_id = 0
category = "__NO_CATEGORY__"



with open('questions.sql', 'w') as sqlfile:
    for question in questions:
        if question[CATEGORY_COLUMN] != category:
            category_id += 1
            category = question[CATEGORY_COLUMN]
            sqlfile.write("insert into categories(category_id, category_name) values (")
            sqlfile.write(str(category_id))
            sqlfile.write(",'")
            sqlfile.write(category.replace("'", "''"))
            sqlfile.write("');\n")
        sqlfile.write("insert into questions(category_id, question, question_type, answer, difficulty) values (")
        sqlfile.write(str(category_id))
        sqlfile.write(",'")
        sqlfile.write(question[QUESTION_COLUMN].replace("'", "''"))
        sqlfile.write("','")
        sqlfile.write(question[QUESTION_TYPE_COLUMN])
        sqlfile.write("','")
        sqlfile.write(question[ANSWER_COLUMN].replace("'", "''"))
        sqlfile.write("',")
        sqlfile.write(question[DIFFICULTY_COLUMN])
        sqlfile.write(");\n")