/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

/**
 *
 * @author ioana
 */
public interface Visitor {
    public void visit(BookDepartment bookDepartment);
    public void visit(MusicDepartment musicDepartment);
    public void visit(SoftwareDepartment softwareDepartment);
    public void visit(VideoDepartment videoDepartment);
}
