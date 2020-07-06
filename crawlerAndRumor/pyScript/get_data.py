#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
从腾讯较真平台获取谣言数据
@author: lqhou
@file: get_data.py
@time: 2020/02/14
"""
from bs4 import BeautifulSoup
import requests
import MySQLdb

#connect mysql db
db = MySQLdb.connect("localhost", "root", "2502128", "2019nCov", charset='utf8' )
cursor = db.cursor()

'''
+-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| id          | int(11)      | NO   | PRI | NULL    |       |
| title       | varchar(255) | YES  |     | NULL    |       |
| mainSummary | varchar(255) | YES  |     | NULL    |       |
| summary     | varchar(255) | YES  |     | NULL    |       |
| body        | varchar(255) | YES  |     | NULL    |       |
| sourceUrl   | varchar(255) | YES  |     | NULL    |       |
| score       | int(5)       | YES  |     | NULL    |       |
| rumorType   | int(1)       | NO   |     | 0       |       |
+-------------+--------------+------+-----+---------+-------+
'''

def getContent():
    id_list = []
    label_list = []
    title_list = []

    label_set = set()
    for i in range(1): #31
        url = "https://vp.fact.qq.com/loadmore?artnum=0&page=" + str(i)
        json_data = requests.get(url).json()

        news_list = json_data['content']

        for news in news_list:
            print(news['id'])
            print(news['title'])
            print(news['abstract']) #body
            print(news['tag']) #mainsummary
            print(news['type']) #rumorType
            
            shortId = int(news['id'][0:5],16)
            newid = shortId % 981800237 #成为11位id




# print(label_set)
# label_id = {'true': '0', 'fake': '1', 'doubt': '1'}

getContent()
## close database connect
db.close()


