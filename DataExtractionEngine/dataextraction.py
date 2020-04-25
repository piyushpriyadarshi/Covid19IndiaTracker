#!/usr/bin/env python

import requests
from bs4 import BeautifulSoup
import mysql.connector
import datetime


page = requests.get("https://www.mohfw.gov.in/", headers={
"authority": "www.mohfw.gov.in",
"method": "GET",
"path": "/",
"scheme": "https",
"accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
"accept-encoding": "gzip, deflate, br",
"accept-language": "en-US,en;q=0.9",
"cache-control": "max-age=0",
"sec-fetch-dest": "document",
"sec-fetch-mode": "navigate",
"sec-fetch-site": "none",
"sec-fetch-user": "?1",
"upgrade-insecure-requests": "1",
"user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36"
})


soup = BeautifulSoup(page.content, 'html.parser')
# table_data=soup.findAll('table')
all_data=soup.find('tbody').findAll('tr')


mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  passwd="piyush@99",
  database="covid",
  auth_plugin='mysql_native_password'
)

for i in range(0,33):
    all_td=all_data[i].findAll('td')
    state=all_td[1].text
    total_case=all_td[2].text
    cured_case=all_td[3].text
    total_death=all_td[4].text
    mycursor = mydb.cursor()
    now=datetime.datetime.now()
    sql = "INSERT INTO state_wise_data(State_Name,Data_date,Total_Cases,Cured_Cases,Total_Death) VALUES (%s , %s , %s , %s , %s)"
    val = (state,now.strftime('%Y-%m-%d'),total_case,cured_case,total_death)
    mycursor.execute(sql, val)
    print(state,"\t",total_case,"\t",cured_case)
    if(state=='West Bengal'):
      break
mydb.commit()

print("Done for Today No Error Reported")

