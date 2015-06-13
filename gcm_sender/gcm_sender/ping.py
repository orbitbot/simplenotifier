# -*- coding: utf-8 -*-

import os, sys, ConfigParser, requests, json

GCM_URL = 'https://gcm-http.googleapis.com/gcm/send'

config = ConfigParser.SafeConfigParser()

def ping():
    cfg_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../config.ini'))
    config.read(cfg_path)
    
    API_KEY = config.get('api', 'key')
    recipient = config.get('recipient', 'token')

    if not API_KEY or not recipient:
        print('Missing values from config file: ' + cfg_path)
        sys.exit(2)

    print('Sending ping:')

    headers = { 'Authorization': 'key=' + API_KEY,
                'Content-Type' : 'application/json' }

    data =  { 'to'   : recipient,
              'data' : { 'message': 'Computer says hi!' } }

    r = requests.post(GCM_URL, data=json.dumps(data), headers=headers, timeout=31)

    print(str(r.status_code) + ' ' + r.url)
    print(prettify_headers(r.headers))
    print(r.text)

def prettify_headers(headers):
    res = ''
    for item in sorted(headers.keys()):
        res += "  '{}' : '{}'".format(item, headers[item]) + "\n"
    return res
