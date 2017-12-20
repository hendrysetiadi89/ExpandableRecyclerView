package com.example.nakama.expandablerecyclerview.expandableadapter;

import com.example.nakama.expandablerecyclerview.expandableadapter.listeners.ExpandCollapseListener;
import com.example.nakama.expandablerecyclerview.expandableadapter.models.ExpandableList;
import com.example.nakama.expandablerecyclerview.expandableadapter.models.ExpandableListPosition;

public class ExpandCollapseController<T> {

    private ExpandCollapseListener<T> listener;
    private ExpandableList<T> expandableList;

    public ExpandCollapseController(ExpandableList<T> expandableList, ExpandCollapseListener<T> listener) {
        this.expandableList = expandableList;
        this.listener = listener;
    }

    /**
     * Collapse a group
     *
     * @param listPosition position of the group to collapse
     */
    private void collapseGroup(ExpandableListPosition listPosition) {
        expandableList.expandedGroupIndexes[listPosition.groupPos] = false;
        if (listener != null) {
            listener.onGroupCollapsed(expandableList.getFlattenedGroupIndex(listPosition) + 1,
                    listener.getChildCount(expandableList.groups.get(listPosition.groupPos)));
        }
    }

    /**
     * Expand a group
     *
     * @param listPosition the group to be expanded
     */
    private void expandGroup(ExpandableListPosition listPosition) {
        expandableList.expandedGroupIndexes[listPosition.groupPos] = true;
        if (listener != null) {
            listener.onGroupExpanded(expandableList.getFlattenedGroupIndex(listPosition) + 1,
                    listener.getChildCount(expandableList.groups.get(listPosition.groupPos)));
        }
    }

    /**
     * @param group the ExpandableGroup being checked for its collapsed state
     * @return true if {@code group} is expanded, false if it is collapsed
     */
    public boolean isGroupExpanded(T group) {
        int groupIndex = expandableList.groups.indexOf(group);
        return expandableList.expandedGroupIndexes[groupIndex];
    }

    /**
     * @param flatPos the flattened position of an item in the list
     * @return true if {@code group} is expanded, false if it is collapsed
     */
    public boolean isGroupExpanded(int flatPos) {
        ExpandableListPosition listPosition = expandableList.getUnflattenedPosition(flatPos);
        return expandableList.expandedGroupIndexes[listPosition.groupPos];
    }

    /**
     * @param flatPos The flat list position of the group
     * @return false if the group is expanded, *after* the toggle, true if the group is now collapsed
     */
    public boolean toggleGroup(int flatPos) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(flatPos);
        boolean expanded = expandableList.expandedGroupIndexes[listPos.groupPos];
        if (expanded) {
            collapseGroup(listPos);
        } else {
            expandGroup(listPos);
        }
        return expanded;
    }

    public boolean toggleGroup(T group) {
        ExpandableListPosition listPos =
                expandableList.getUnflattenedPosition(expandableList.getFlattenedGroupIndex(group));
        boolean expanded = expandableList.expandedGroupIndexes[listPos.groupPos];
        if (expanded) {
            collapseGroup(listPos);
        } else {
            expandGroup(listPos);
        }
        return expanded;
    }

}