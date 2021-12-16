class DisplayRandom extends DisplayRandomBase {    
    
    public DisplayRandom(String[] filename) {
        super(filename);
    }

    @Override
    public Plane[] sort() {
        quicksort(this.getData(), 0, this.getData().length - 1);
        return this.getData(); 
    }

    public void quicksort(Plane[] planes, int leftIndex, int rightIndex) {

        // Inplace Quicksort -> pseudocode from week 2 lecture slide 62 and Introduction to Algorithms, page 171

        if (leftIndex >= rightIndex) {
            return;
        }

        int partitionIndex = randomisedPartition(planes, leftIndex, rightIndex);
        
        quicksort(planes, leftIndex, partitionIndex - 1);
        quicksort(planes, partitionIndex + 1, rightIndex);         
    }

    public int randomisedPartition(Plane[] planes, int leftIndex, int rightIndex) {

        // randomised partition pseudocode from Introduction to Algorithms, page 179

        double compressedRandom = Math.random() * (rightIndex - leftIndex); 
        int randomIndex = ((int) compressedRandom) + leftIndex; 
                 
        Plane temp = planes[randomIndex];
        planes[randomIndex] = planes[rightIndex];
        planes[rightIndex] = temp; 

        return LomutoPartitionArray(planes, leftIndex, rightIndex);
    }

    public int LomutoPartitionArray(Plane[] planes, int leftIndex, int rightIndex) {

        // Lomuto partition pesudocode from Introduction to Algorithms, page 171

        Plane pivot = planes[rightIndex];

        int i = leftIndex - 1;

        for (int j = leftIndex; j < rightIndex; j++) {
            if (planes[j].compareTo(pivot) < 0) {
                
                i++;

                Plane temp = planes[i];
                planes[i] = planes[j];
                planes[j] = temp;
            }
        }

        Plane temp = planes[i + 1];
        planes[i + 1] = planes[rightIndex];
        planes[rightIndex] = temp;

        return i + 1;
    }   
}

class DisplayPartiallySorted extends DisplayPartiallySortedBase {

    public DisplayPartiallySorted(String[] scheduleFilename, String[] extraPlanesFilename) {
        super(scheduleFilename, extraPlanesFilename);
    }

    @Override
    Plane[] sort() {
        quicksort(this.getExtraPlanes(), 0, this.getExtraPlanes().length - 1); 
        return merge(this.getSchedule(), this.getExtraPlanes()); 
    }

    public void quicksort(Plane[] planes, int leftIndex, int rightIndex) {

        // inplace quicksort pseudocode from week 2 lecture slide 62

        if (rightIndex <= leftIndex) {
            return;
        }

        int partitionIndex = randomisedPartition(planes, leftIndex, rightIndex);

        quicksort(planes, leftIndex, partitionIndex - 1);
        quicksort(planes, partitionIndex + 1, rightIndex);
    }

    public int randomisedPartition(Plane[] planes, int leftIndex, int rightIndex) {

        // randomised partition pseudocode from Introduction to Algorithms, page 179
        
        double compressedRandom = Math.random() * (rightIndex - leftIndex);
        int randomIndex = ((int) compressedRandom) + leftIndex;

        Plane temp = planes[randomIndex];
        planes[randomIndex] = planes[rightIndex];
        planes[rightIndex] = temp;
        
        return LomutoPartitionArray(planes, leftIndex, rightIndex);
    }

    public int LomutoPartitionArray(Plane[] planes, int leftIndex, int rightIndex) {

        // Lomuto partition pesudocode from Introduction to Algorithms, page 171

        Plane pivot = planes[rightIndex];

        int i = leftIndex - 1;

        for (int j = leftIndex; j < rightIndex; j++) {
            if (planes[j].compareTo(pivot) < 0) {
                i++;

                Plane temp = planes[i];
                planes[i] = planes[j];
                planes[j] = temp;
            }
        }

        Plane temp = planes[i + 1];
        planes[i + 1] = planes[rightIndex];
        planes[rightIndex] = temp;

        return i + 1;
    }
    
    public Plane[] merge(Plane[] sortedOne, Plane[] sortedTwo) {

        // merge component of mergesort pesudocode from week 2 lecture slide 34

        int i = 0, j = 0, k = 0;
        Plane[] mergedArray = new Plane[sortedOne.length + sortedTwo.length];

        while (i < sortedOne.length && j < sortedTwo.length) {
            if (sortedOne[i].compareTo(sortedTwo[j]) < 0) {
                mergedArray[k] = sortedOne[i];
                i++; k++;
            } else {
                mergedArray[k] = sortedTwo[j];
                j++; k++;
            }
        }
        
        while (i < sortedOne.length) {
            mergedArray[k] = sortedOne[i];
            i++; k++;
        }

        while (j < sortedTwo.length) {
            mergedArray[k] = sortedTwo[j];
            j++; k++;
        }

        return mergedArray;
    }
}