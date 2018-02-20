public interface AStarSearcher {

    public void addInOpenList(Node node);

    public Node pollFromOpenList();

    public void setClosed(Node node);

    //

}
