/**
 * This defines the Schedule class which is meant to describe a user's class shedule and
 * recommend spots based on it
 *
 * Last Modified: April 6, 2019
 *
 */

package logic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.lang.Math;
import java.util.ArrayList;

public class Schedule implements Serializable{
    private StudySpot[] day1Schedule; // each element represents an hour of the day

    /**
     * Default constructor for Schedule object which creates an empty Schedule
     */
    public Schedule() {
        day1Schedule = new StudySpot[15];
        for(int i = 0;i < 15;i++) {
            day1Schedule[i] = null;
        }
    }

    /**
     * Copy constructor for Schedule object
     */
    public Schedule(Schedule scheduleToCopy) {
        this.day1Schedule = new StudySpot[15];
        for(int i = 8;i < 21;i++) {
            this.setClass(i, scheduleToCopy.getClass(i));
        }
    }

    /**
     * Returns the StudySpot object representing the class location at the given time or null if there isn't one
     * @param timeIn24Hour: int representing the hour of the day, between 8:00 and 20:00 inclusive, to get the class at
     */
    public StudySpot getClass(int timeIn24Hour){
        if((timeIn24Hour >= 8 && timeIn24Hour <= 20) && day1Schedule[timeIn24Hour - 7] != null) {
            return new StudySpot(day1Schedule[timeIn24Hour - 7]);
        }
        else {
            return null;
        }
    }

    /**
     * Sets the class location at the given time to the given StudySpot object
     * @param timeIn24Hour: int representing the hour of the day, between 8:00 and 20:00 inclusive, to add the class at
     * @param studySpotToAdd: StudySpot object representing the location of the class at the given time
     */
    public void setClass(int timeIn24Hour, StudySpot studySpotToAdd){

        if(timeIn24Hour >= 8 && timeIn24Hour <= 20) {
            if(studySpotToAdd != null) {
                day1Schedule[timeIn24Hour - 7] = new StudySpot(studySpotToAdd);
            }
            else {
                day1Schedule[timeIn24Hour - 7] = null;
            }
        }
    }

    /**
     * Returns a double value describing the average distance between the given StudySpot and the classes surrounding
     * the given time
     * @param studySpotToCompare: StudySpot object to check for distance to classes
     * @param hour: int representing the the time in 24 hours to check around
     * @param numOfSurroundingSpots: number of classes immediately on either side of the given time
     */
    private double compareDistance(StudySpot studySpotToCompare, int hour, int numOfSurroundingSpots){

        double averageDistance = 0.0;

        // equations are simply pythagorean theorem
        if(day1Schedule[hour - 8] != null) {
            averageDistance += Math.sqrt((day1Schedule[hour - 8].averageX() - studySpotToCompare.averageX()) * (day1Schedule[hour - 8].averageX() - studySpotToCompare.averageX()) +
                    (day1Schedule[hour - 8].averageY() - studySpotToCompare.averageY()) * (day1Schedule[hour - 8].averageY() - studySpotToCompare.averageY()));
        }
        if(day1Schedule[hour - 6] != null) {
            averageDistance += Math.sqrt((day1Schedule[hour - 6].averageX() - studySpotToCompare.averageX()) * (day1Schedule[hour - 6].averageX() - studySpotToCompare.averageX()) +
                    (day1Schedule[hour - 6].averageY() - studySpotToCompare.averageY()) * (day1Schedule[hour - 6].averageY() - studySpotToCompare.averageY()));
        }
        averageDistance = averageDistance / numOfSurroundingSpots;


        return averageDistance;

    }

    /**
     * Returns an ArrayList of StudySpot ordered from the best to the worst based on the survey results and schedule
     * @param spotList: StudySpotList object containing the study spots and the user's survey results
     */
    public ArrayList<StudySpot> getBestSpotsWithSchedule(StudySpotList spotList) {

        int numOfSurroundingSpots = 0;

        int hour = LocalDateTime.now().getHour();
        if(hour < 8 || hour > 20) {
            hour = 8;
        }

        // finds the next time the user is free to study, looping to the next morning if
        // there are no times remaining in the current day
        for(int i = 1;i < 14;i++) {
            if(day1Schedule[hour - 7] == null) {
                if(day1Schedule[hour - 8] != null) {
                    numOfSurroundingSpots++;
                }
                if(day1Schedule[hour - 6] != null) {
                    numOfSurroundingSpots++;
                }
            }
            if(numOfSurroundingSpots > 0 || day1Schedule[hour - 7] == null) {
                break;
            }

            hour++;
            if(hour > 20) {
                hour = 8;
            }
        }

        ArrayList<StudySpot> bestSpotList = spotList.getBestStudySpots();
        ArrayList<Double> distances = new ArrayList<Double>();

        // list is not rearranged if the user has no classes or no availability
        if(numOfSurroundingSpots > 0) {
            for (int i = 0; i < bestSpotList.size(); i++) {
                distances.add(this.compareDistance(bestSpotList.get(i), hour, numOfSurroundingSpots));
            }

            Double temp;
            StudySpot tempss;

            // rearranges the best study spot list, taking substantial distance differences into consideration
            for (int i = 1; i < bestSpotList.size(); i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (distances.get(j) > distances.get(j + 1) + 40.0) {
                        temp = distances.get(j);
                        distances.set(j, distances.get(j + 1));
                        distances.set(j + 1, temp);
                        tempss = bestSpotList.get(j);
                        bestSpotList.set(j, bestSpotList.get(j + 1));
                        bestSpotList.set(j + 1, tempss);
                    }
                }
            }
        }

        return bestSpotList;

    }

}
