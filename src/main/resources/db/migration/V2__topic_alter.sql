ALTER table topic drop column created_;
ALTER table topic drop column updated_;

ALTER table topic add column created_ timestamp;
ALTER table topic add column modified_ timestamp default now();
