# ParaHuntersExamProject
This is my project for the Java Web course final exam. It is a website, providing every hunter with Creature wiki, Store for used weapons and gadgets and a social media called "PostIt!". I decided to change the name from techStore to paraHunters a little too late so the directory is called techStore.

Context: ParaHunters is a global organisation which is dedicated to fighting paranormal creatures. It was established in 1817 and its purpose is to teach people how to become hunters and to create a community in which they can help each other. The website is a multi-purpose platform for legitimate hunters, where they can sell the weapons they are no longer using, check creatures' wiki and post memories in "PostIt".

Hunter Code(one of the variables in the UserEntity class) explained: 
	The Hunter Code is a unique code, stored in the ParaHunters' db, which every hunter receives when certified for hunting. Basically it proves you are legitimate and lets you use the website. Some of the hunters are given the admin role through their Hunter Code as well. The problem is that ParaHunters is a fictional organisation, so there are no certificates for hunting and there are no Hunter Codes either. So I simplified the Hunter Code to 1111 for Admin and 0000 for User. If Hunter Codes were real, bans would be much better, because when banned, the hunter gets his Code deleted from the ParaHunters database, thus he is no longer a legitimate hunter and cannot access the website.
