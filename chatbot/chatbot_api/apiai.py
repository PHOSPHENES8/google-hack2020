#-*- encoding=utf-8 -*-
import json
import os
import requests
import urllib

# A function for wake word(s) or phrase
def wakeWord(text):
    WAKE_WORDS = ['hey computer', 'okay computer', 'Cosa']  # a list of wake words

    text = text.lower()  # Converting the text to all lower case words
    # Check to see if the users command/text contains a wake word/phrase
    for phrase in WAKE_WORDS:
        if phrase in text:
            return True

    # if the wake word isn't found in the text
    return False

# There are some network issues when using Dialogflow
def setup_dialogflow():
    abspath = os.path.abspath(os.path.dirname(__file__))
    json_file = abspath + '/Cosa-cc2052ad69a1.json'
#    print(json_file)
    os.system("export GOOGLE_APPLICATION_CREDENTIALS=json_file")

def implicit_dialogflow():
    from google.cloud import storage

    # If you don't specify credentials when constructing the client, the
    # client library will look for credentials in the environment.
    storage_client = storage.Client()

    # Make an authenticated API request
    buckets = list(storage_client.list_buckets())
    print(buckets)

def explicit_dialogflow():
    from google.cloud import storage

    # Explicitly use service account credentials by specifying the private key
    # file.
    storage_client = storage.Client.from_service_account_json(
        './Cosa-cc2052ad69a1.json')

    # Make an authenticated API request
    buckets = list(storage_client.list_buckets())
    print(buckets)

def run_quickstart_dialogflow():
    # [START storage_quickstart]
    # Imports the Google Cloud client library
    from google.cloud import storage

    # Instantiates a client
    storage_client = storage.Client()

    # The name for the new bucket
    bucket_name = "my-new-bucket"

    # Creates the new bucket
    bucket = storage_client.create_bucket(bucket_name)

    print("Bucket {} created.".format(bucket.name))
    # [END storage_quickstart]

# Tuling
def tuling_msg(): 
    tuling_key = 'c2bf9c867aa24b8daaf501dc6deec3e9'

    api_url = "http://openapi.tuling123.com/openapi/api/v2"
    text_input = input('我： ')

    req = {
        "perception":
        {
            "inputText":
            {
                "text": text_input
            },
    
            "selfInfo":
            {
                "location":
                {
                    "city": "深圳",
                    "province": "广东",
                    "street": "桃源街道"
                }
            }
        },

        "userInfo": 
        {
            "apiKey": "c2bf9c867aa24b8daaf501dc6deec3e9",
            "userId": "OnlyUseAlphabet"
        }
    }

    req = json.dumps(req).encode('utf8')
    # print(req)
    
    http_post = urllib.request.Request(api_url, data=req, headers={'content-type': 'application/json'})
    response = urllib.request.urlopen(http_post)
    response_str = response.read().decode('utf8')
    # print(response_str)
    response_dic = json.loads(response_str)
    # print(response_dic)
    
    intent_code = response_dic['intent']['code']
    results_text = response_dic['results'][0]['values']['text']
    print('Turing：' + results_text)
    #print('code：' + str(intent_code))


if __name__ == "__main__":

    # Record the audio
    # text = recordAudio()

    # Check for the wake word/phrase
    #if(wakeWord(text) == True):
    while True:
        tuling_msg()
    #    explicit_dialogflow()
 
