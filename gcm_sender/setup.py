from setuptools import setup
import sys

def disable_publishing():
    argv = sys.argv

    for command in ['register', 'upload']:
        if command in argv:
            print('setup.py "%(command)s" has been disabled.' % {'command': command})
            sys.exit(2)

disable_publishing()

setup(
    name='gcm-sender',
    version='0.0.1',
    author='Patrik Johnson',
    author_email='patrik@zebros.fi',
    packages=['gcm_sender'],
    url='https://github.com/orbitbot/simplenotifier',
    license='ISC',
    description='POC CLI for sending GCM messages',
    entry_points = {
        'console_scripts': ['gcm-ping=gcm_sender.ping:ping'],
    },
    install_requires = ['requests']
)