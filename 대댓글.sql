alter table ONELINEREVIEW  add (parent_onelinereviewno number);
update ONELINEREVIEW set parent_onelinereviewno = onelinereviewno where parent_onelinereviewno is null;