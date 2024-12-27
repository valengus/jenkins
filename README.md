# Jenkins ansible role

### Test role
```bash
python3.9 -m venv .venv
source .venv/bin/activate
pip install pip --upgrade
pip install -r requirements.txt

molecule test --all
```